package de.koanam.textparser.test;

import java.util.List;

import de.koanam.textparser.annotations.ParseableAttribute;
import de.koanam.textparser.annotations.ParseableAttributeLength;
import de.koanam.textparser.annotations.ParseableElement;
import de.koanam.textparser.annotations.ParseableList;

@ParseableElement
public class RootData {

	@ParseableAttributeLength(length=5)
	@ParseableAttribute(order = 20)
	private String text;

	@ParseableList(amountIndicator=1)
	@ParseableAttribute(order = 30)
	private List<ChildData> childData;

	@ParseableAttributeLength(length=3)
	@ParseableAttribute(order = 10)
	private Integer number;

	private Integer nothing;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getNothing() {
		return nothing;
	}

	public void setNothing(Integer nothing) {
		this.nothing = nothing;
	}

	@Override
	public String toString() {
		return "RootData [number=" + number + ", text=" + text + ", childData=" + childData + ", nothing=" + nothing
				+ "]";
	}

}
