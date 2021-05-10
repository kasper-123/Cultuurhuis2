package be.vdab.cultuurhuis.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.TYPE;
@Retention(RUNTIME)
@Target({TYPE, ANNOTATION_TYPE})
@Constraint(validatedBy = NieuweKlantValidator.class)

public @interface NieuweKlant {

String message() default "{be.vdab.Klant.message}";
Class<?>[] groups() default {};
Class<? extends Payload>[] payload() default {};
}
