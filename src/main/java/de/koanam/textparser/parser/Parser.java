package de.koanam.textparser.parser;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.koanam.textparser.annotations.AnnotatedFieldComparator;
import de.koanam.textparser.annotations.ParseableAttribute;
import de.koanam.textparser.annotations.ParseableAttributeLength;
import de.koanam.textparser.annotations.ParseableElement;
import de.koanam.textparser.annotations.ParseableList;

public class Parser<T> {

	private Class<T> klasse;

	public Parser(Class<T> klasse) {
		this.klasse = klasse;
	}

	public T parse(ParseableDataInputStream data) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {

		T instance = this.klasse.getDeclaredConstructor().newInstance();
		Field[] fields = instance.getClass().getDeclaredFields();
		Arrays.sort(fields, new AnnotatedFieldComparator());

		for (Field field : fields) {
			field.setAccessible(true);
			ParseableAttribute parseableAttribute = field.getAnnotation(ParseableAttribute.class);
			if (parseableAttribute != null) {
				
				// Eigenes Element parsen
				if(field.getType().isAnnotationPresent(ParseableElement.class)) {
					Parser<?> parser=new Parser<>(field.getType());
					Object o=parser.parse(data);
					field.set(instance, o);
				}
				
				// Primitives Attribut parsen
				ParseableAttributeLength parseableAttributeLength = field.getAnnotation(ParseableAttributeLength.class);
				if (parseableAttributeLength != null) {
					parseAttribute(data, instance, field, parseableAttributeLength);
				}
				
				// List parsen
				ParseableList parseableList=field.getAnnotation(ParseableList.class);
				if(parseableList != null) {
					Integer size=Integer.valueOf(data.readData(parseableList.amountIndicator()));
					List<Object> list=new ArrayList<>(size);
					if(parseableList.elementLength()==0) {
						ParameterizedType type = (ParameterizedType) field.getGenericType();
						Class<?> typeclass = (Class<?>) type.getActualTypeArguments()[0];
						Parser<?> parser=new Parser<>(typeclass);
						for(int i=0; i<size; i++) {
							Object o=parser.parse(data);
							list.add(o);
						}
					}else {
						for(int i=0; i<size; i++) {
							ParameterizedType type = (ParameterizedType) field.getGenericType();
							Class<?> typeclass = (Class<?>) type.getActualTypeArguments()[0];
							Object o=typeclass.getConstructor(String.class).newInstance(data.readData(parseableList.elementLength()));
							list.add(o);
						}
					}
					field.set(instance, list);
					
				}
			}
		}
		return instance;
	}

	private void parseAttribute(ParseableDataInputStream data, T instance, Field field,
			ParseableAttributeLength parseableAttributeLength) throws InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, IOException {
		if (parseableAttributeLength.length() > 0) {
			Object attribute = field.getType().getConstructor(String.class).newInstance(data.readData(parseableAttributeLength.length()).trim());
			field.set(instance, attribute);
		}
		if (parseableAttributeLength.lengthIndicator() > 0) {
			Integer length = Integer.valueOf(data.readData(parseableAttributeLength.lengthIndicator()));
			Object attribute = field.getType().getConstructor(String.class).newInstance(data.readData(length).trim());
			field.set(instance, attribute);
		}
	}

}
