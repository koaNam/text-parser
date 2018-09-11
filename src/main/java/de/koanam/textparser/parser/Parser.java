package de.koanam.textparser.parser;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import de.koanam.textparser.annotations.ParsableAttribute;
import de.koanam.textparser.annotations.ParsableElement;



public class Parser<T> {

	private Class<T> klasse;

	public Parser(Class<T> klasse) {
		this.klasse = klasse;
	}

	public T parse(String data) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {

		T instance = this.klasse.getDeclaredConstructor().newInstance();

		for (Field field : instance.getClass().getFields()) {
			ParsableAttribute annotation = field.getAnnotation(ParsableAttribute.class);
			if (annotation != null) {
				String part = data.substring(annotation.start(), annotation.start() + annotation.length());

				if (field.getType().isAnnotationPresent(ParsableElement.class)) {
					Object o =new Parser<>(field.getType()).parse(part);
					field.set(instance, o);
				} else {
					Object o = field.getType().getConstructor(String.class).newInstance(part);
					field.set(instance, o);
				}
			}
		}
		return instance;
	}

}
