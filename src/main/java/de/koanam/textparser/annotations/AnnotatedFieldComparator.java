package de.koanam.textparser.annotations;

import java.lang.reflect.Field;
import java.util.Comparator;

public class AnnotatedFieldComparator implements Comparator<Field> {

	@Override
	public int compare(Field o1, Field o2) {
		ParseableAttribute a1 = o1.getAnnotation(ParseableAttribute.class);
		ParseableAttribute a2 = o2.getAnnotation(ParseableAttribute.class);
		if(a1 == null && a2 ==null){
			return 0;
		}
		else if (a1 == null) {
			return -1;
		} else if(a2 == null) {
			return 1;
		}else {
			return a1.order()-a2.order();
		}
	}

}
