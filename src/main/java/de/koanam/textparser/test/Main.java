package de.koanam.textparser.test;

import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import de.koanam.textparser.parser.ParseableDataInputStream;
import de.koanam.textparser.parser.Parser;

public class Main {

	public static void main(String[] args) {

		Parser<RootData> parser = new Parser<>(RootData.class);
		
		RootData data;
		try {
			data = parser.parse(new ParseableDataInputStream("100START15.25TEST410203040".getBytes()));

			System.out.println(data.toString());
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
