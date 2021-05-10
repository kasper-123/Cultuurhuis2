package be.vdab.cultuurhuis.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = TekstVakValidator.class)
public @interface TekstVak {
	String message() default "{be.vdab.TekstVak.message}";
Class<?>[] groups() default {};
Class<? extends Payload>[] payload() default {};
}
