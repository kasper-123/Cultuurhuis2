package be.vdab.cultuurhuis.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({TYPE, ANNOTATION_TYPE,FIELD})
@Constraint(validatedBy =BeschikbaarReserveerValidator.class)
public @interface BeschikbaarReserveer {
String message() default "{be.vdab.BeschikbaarReserveer.message= teweinige beschukikbare plaatsen}";
Class<?>[] groups() default {};
Class<? extends Payload>[] payload() default {};
}
