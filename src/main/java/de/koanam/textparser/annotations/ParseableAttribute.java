package de.koanam.textparser.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Gibt an, dass das Attribut geparst werden soll.
 * Hat das Attribut einen primitiven Typen muss zusätzlich {@link ParseableAttributeLength} verwendet werden
 * Ist das Attribut eine Liste muss zusätzlich {@link ParseableList} verwendet werden
 * @author Flo
 *
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ParseableAttribute {

	int order();
	
}
