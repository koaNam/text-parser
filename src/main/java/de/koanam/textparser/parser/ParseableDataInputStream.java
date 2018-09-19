package de.koanam.textparser.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ParseableDataInputStream extends ByteArrayInputStream{

	public ParseableDataInputStream(byte[] buf) {
		super(buf);
	}
	
	public String readData(int length) throws IOException {
		byte[] data=new byte[length];
		this.read(data);
		return new String(data);
	}

}
