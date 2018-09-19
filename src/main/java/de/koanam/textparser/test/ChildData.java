package de.koanam.textparser.test;

import java.util.List;

import de.koanam.textparser.annotations.ParseableAttribute;
import de.koanam.textparser.annotations.ParseableAttributeLength;
import de.koanam.textparser.annotations.ParseableElement;
import de.koanam.textparser.annotations.ParseableList;

@ParseableElement
public class ChildData {

	@ParseableAttributeLength(length=4)
	@ParseableAttribute(order = 10)
	private Double komma;

	@ParseableAttributeLength(length=4)
	@ParseableAttribute(order = 20)
	private String test;

	@ParseableList(amountIndicator = 1, elementLength=2)
	@ParseableAttribute(order=30)
	private List<Integer> numberList;

	public Double getKomma() {
		return komma;
	}

	public void setKomma(Double komma) {
		this.komma = komma;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public List<Integer> getNumberList() {
		return numberList;
	}

	public void setNumberList(List<Integer> numberList) {
		this.numberList = numberList;
	}

	@Override
	public String toString() {
		return "ChildData [komma=" + komma + ", test=" + test + ", numberList=" + numberList + "]";
	}

}
