package de.koanam.textparser.generator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import de.koanam.textparser.annotations.AnnotatedFieldComparator;
import de.koanam.textparser.annotations.ParseableAttribute;
import de.koanam.textparser.annotations.ParseableAttributeLength;
import de.koanam.textparser.annotations.ParseableElement;
import de.koanam.textparser.annotations.ParseableList;

public class Generator<T> {

	public byte[] generate(T instance) throws IllegalArgumentException, IllegalAccessException, IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		this.generate(instance, result);
		return result.toByteArray();
	}

	protected void generate(Object instance, ByteArrayOutputStream result)
			throws IllegalArgumentException, IllegalAccessException, IOException {
		Field[] fields = instance.getClass().getDeclaredFields();
		Arrays.sort(fields, new AnnotatedFieldComparator());
		for (Field field : fields) {
			field.setAccessible(true);
			ParseableAttribute parseableAttribute = field.getAnnotation(ParseableAttribute.class);

			if (parseableAttribute != null) {
				// Eigenes Element schreiben
				if (field.getType().isAnnotationPresent(ParseableElement.class)) {
					Generator<?> generator = new Generator<>();
					generator.generate(field.get(instance), result);
				}

				// Primitiven Typen schreiben
				ParseableAttributeLength parseableAttributeLength = field.getAnnotation(ParseableAttributeLength.class);
				if (parseableAttributeLength != null) {
					if (parseableAttributeLength.length() > 0) {
						byte[] value = field.get(instance).toString().getBytes();
						if (field.getType().equals(String.class)) {
							value =this.converteString(value, parseableAttributeLength.length());
						} else {
							value=this.converteNumber(value, parseableAttributeLength.length());
						}
						result.write(value);
					}
					if (parseableAttributeLength.lengthIndicator() > 0) {
						byte[] value = field.get(instance).toString().getBytes();
						byte[] lengthValue = Integer.valueOf(value.length).toString().getBytes();
						lengthValue=this.converteNumber(lengthValue, parseableAttributeLength.lengthIndicator());
						result.write(lengthValue);

						if (field.getType().equals(String.class)) {
							value=this.converteString(value, value.length);
						} else {
							value=this.converteNumber(value, value.length);
						}
						result.write(value);
					}
				}

				ParseableList parseableList = field.getAnnotation(ParseableList.class);
				if (parseableList != null) {
					List<?> list = (List<?>) field.get(instance);
					int size = list.size();
					byte[] valueLength=converteNumber(Integer.valueOf(size).toString().getBytes(), parseableList.amountIndicator());
					result.write(valueLength);
					if (parseableList.elementLength() == 0) {
						Generator<?> generator = new Generator<>();
						for (int i = 0; i < size; i++) {
							generator.generate(list.get(i), result);
						}
					} else {
						for (int i = 0; i < size; i++) {
							byte[] value = list.get(i).toString().getBytes();
							if (field.getType().equals(String.class)) {
								value=this.converteString(value, parseableList.elementLength());
							} else {
								value=this.converteNumber(value, parseableList.elementLength());
							}
							result.write(value);
						}
					}
				}
			}

		}
	}

	private byte[] converteString(byte[] value, int length) {
		byte[] val = new byte[length];
		System.arraycopy(value, 0, val, 0, value.length);
		for (int i = value.length; i < length; i++) {
			val[i] = ' ';
		}
		return val;
	}

	private byte[] converteNumber(byte[] value, int length) {
		byte[] val = new byte[length];
		System.arraycopy(value, 0, val, length-value.length, value.length);
		for (int i = 0; i < length-value.length; i++) {
			val[i] = '0';
		}
		return val;
	}

}
