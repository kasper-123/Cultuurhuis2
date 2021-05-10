package be.vdab.cultuurhuis.constraints;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Positive
@Max(10000)
@Constraint(validatedBy = GetalValidator.class)
public @interface Getal {
	String message() default "{be.vdab.Getal.message}";
	Class<?>[]groups() default {};
	Class<? extends Payload>[]payload() default {};
}
