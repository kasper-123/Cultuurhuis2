package be.vdab.cultuurhuis.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE,ElementType.FIELD})
@Constraint(validatedBy = wachtwoordValidator.class )
@TekstVak
public @interface Wachtwoord {String message() default "{be.vdab.WachtWoord.message}";
Class<?>[] groups() default {};
Class<? extends Payload>[] payload() default {};
}
