package de.koanam.textparser.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Gibt die Länge der Liste an, in die die Daten geparste werden sollen
 * Ist elementLength gesetzt haben alle Elemente die gleiche Länge. Das ist nur bei primitiven Datentypen notwendig
 * AmountIndicator gibt an wie die Anzahl an Attributen der Liste angegeben ist
 * @author Flo
 *
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ParseableList {
	
	int amountIndicator();
	
	int elementLength() default 0;
	
}
