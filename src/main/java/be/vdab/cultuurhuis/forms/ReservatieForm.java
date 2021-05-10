package be.vdab.cultuurhuis.forms;

import be.vdab.cultuurhuis.constraints.Getal;
import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Voorstelling;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class ReservatieForm {
	@PositiveOrZero
	int plaatsen;
	
	@Valid
	Voorstelling voorstelling;
	
	@Valid
	Klant klant;

public int getPlaatsen() {
	return plaatsen;
}

public Voorstelling getVoorstelling() {
	return voorstelling;
}

public Klant getKlant() {
	return klant;
}
}
