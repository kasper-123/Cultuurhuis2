package be.vdab.cultuurhuis.forms;

import be.vdab.cultuurhuis.constraints.Getal;
import be.vdab.cultuurhuis.constraints.NieuweKlant;
import be.vdab.cultuurhuis.constraints.TekstVak;
import be.vdab.cultuurhuis.constraints.Wachtwoord;
import be.vdab.cultuurhuis.domain.Klant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@NieuweKlant
public class NieuweKlantForm {
public NieuweKlantForm(String voorNaam, String familieNaam, String straat, int huisNr, int postCode, String gemeente, String gebruikersnaam, String paswoord, String paswoord2) {
	this.voorNaam = "";
	this.familieNaam = "";
	this.straat = "";
	this.huisNr = 0;
	this.postCode = 00;
	this.gemeente = "";
	this.gebruikersnaam = "";
	this.paswoord = "";
	this.paswoord2 = "";
}

@TekstVak
private String voorNaam;
@TekstVak
private String familieNaam;
@TekstVak
private String straat;
@Getal
private int huisNr;
@Getal
private int postCode;
@TekstVak
private String gemeente;
@TekstVak
private String  gebruikersnaam;
@Wachtwoord
private String paswoord;
@Wachtwoord
private String paswoord2;

public Klant getklant(){
		Klant nieuweklant=
		new Klant(voorNaam,familieNaam,straat,huisNr,postCode,
		gemeente,gebruikersnaam,paswoord);
		return nieuweklant;
}
		


public String getPaswoord() {
	return paswoord;
}


public String getVoorNaam() {
	return voorNaam;
}

public String getFamilieNaam() {
	return familieNaam;
}

public String getStraat() {
	return straat;
}

public int getHuisNr() {
	return huisNr;
}

public int getPostCode() {
	return postCode;
}

public String getGemeente() {
	return gemeente;
}

public String getGebruikersnaam() {
	return gebruikersnaam;
}

public String getPaswoord2() {
	return paswoord2;
}
}

