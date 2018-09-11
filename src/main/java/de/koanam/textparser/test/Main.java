package de.koanam.textparser.test;

import java.lang.reflect.InvocationTargetException;

import de.koanam.textparser.parser.Parser;

public class Main {

	public static void main(String[] args) {

		Parser<RootData> parser = new Parser<>(RootData.class);

		RootData data;
		try {
			data = parser.parse("100START5.25TEST");

			System.out.println(data.toString());
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}

}
