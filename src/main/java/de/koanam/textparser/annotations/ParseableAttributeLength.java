package de.koanam.textparser.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Gibt die L�nge des Attributes an
 * Wird length gesetzt hat das Attribut eine feste L�nge
 * Wird lengthIndicator gesetzt, steht vor dem eigentlichen Attribut noch die L�nge
 * @author Flo
 *
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ParseableAttributeLength {
	
	int length() default 0;
	
	int lengthIndicator() default 0;
}
