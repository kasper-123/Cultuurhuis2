package be.vdab.cultuurhuis.constraints;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.forms.NieuweKlantForm;
import be.vdab.cultuurhuis.repositories.KlantRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NieuweKlantValidator implements ConstraintValidator<NieuweKlant, Klant> {


	@Override
public void initialize(NieuweKlant constraintAnnotation) {

}

@Override
public boolean isValid(Klant klant, ConstraintValidatorContext constraintValidatorContext) {
	if (klant==null){
		return true;
	}
	//	if(klantRepository.findByGebruikersnaam(klant.getGebruikersnaam()).isPresent()){
	//return false;}
	
	
	return true;
	
	
}


}
