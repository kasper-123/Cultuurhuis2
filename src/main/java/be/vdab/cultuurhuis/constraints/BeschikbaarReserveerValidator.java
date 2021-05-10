package be.vdab.cultuurhuis.constraints;

import be.vdab.cultuurhuis.domain.Reservatie;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class BeschikbaarReserveerValidator implements ConstraintValidator<BeschikbaarReserveer, Reservatie> {


@Override
public void initialize(BeschikbaarReserveer constraintAnnotation) {

}

@Override
public boolean isValid(Reservatie reservatie, ConstraintValidatorContext constraintValidatorContext) {
	if (reservatie== null){
		return true;
	}
	if(reservatie.getVoorstelling().getVrijeplaatsen() <reservatie.getPlaatsen() ){
		return false;
	}
	if(reservatie.getPlaatsen()<0){
		return false;
	}
	
	return true;
}
}
