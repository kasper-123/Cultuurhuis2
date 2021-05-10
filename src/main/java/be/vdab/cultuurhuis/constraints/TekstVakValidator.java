package be.vdab.cultuurhuis.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TekstVakValidator  implements ConstraintValidator<TekstVak,String> {
@Override
public void initialize(TekstVak constraintAnnotation) {
}
	
	
	@Override
public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
	if (s ==null){
	
	
	return false;}
	
	if(s.trim().equals("")){
		return false;
	}
	if (s.length()>20){
		return false;
	}




return true;
}
}
