package be.vdab.cultuurhuis.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GetalValidator implements ConstraintValidator<Getal, Integer> {

@Override
public void initialize(Getal constraintAnnotation) {


}

@Override
public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
	if(integer==null){
		return true;
	}
	
	if (integer <= 0){
		return false;
	}

	
	
	return true;
}
}
