package be.vdab.cultuurhuis.forms;

import be.vdab.cultuurhuis.constraints.Getal;
import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Voorstelling;

import javax.validation.Valid;

public class ReservatieForm {
	@Getal
	int plaatsen;
	
	@Valid
	Voorstelling voorstelling;
	
	@Valid
	Klant klant;
}
