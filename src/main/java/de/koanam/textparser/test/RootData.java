package de.koanam.textparser.test;

import de.koanam.textparser.annotations.ParsableAttribute;
import de.koanam.textparser.annotations.ParsableElement;

@ParsableElement
public class RootData {

	@ParsableAttribute(start = 0, length = 3)
	public Integer number;

	@ParsableAttribute(start = 3, length = 5)
	public String text;

	@ParsableAttribute(start = 8, length = 8)
	public ChildData childData;

	public Integer nothing;

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
