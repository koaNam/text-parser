package de.koanam.textparser.test;

import de.koanam.textparser.annotations.ParsableAttribute;
import de.koanam.textparser.annotations.ParsableElement;

@ParsableElement
public class ChildData {

	@ParsableAttribute(start = 0, length = 4)
	public Double komma;

	@ParsableAttribute(start=4, length=4)
	public String test;

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

	@Override
	public String toString() {
		return "ChildData [komma=" + komma + ", test=" + test + "]";
	}

}
