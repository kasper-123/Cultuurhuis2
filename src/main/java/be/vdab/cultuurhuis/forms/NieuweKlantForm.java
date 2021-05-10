package be.vdab.cultuurhuis.forms;

import be.vdab.cultuurhuis.constraints.Getal;
import be.vdab.cultuurhuis.constraints.NieuweKlant;
import be.vdab.cultuurhuis.constraints.TekstVak;
import be.vdab.cultuurhuis.constraints.Wachtwoord;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@NieuweKlant
public class NieuweKlantForm {

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
@Email
private String  gebruikersnaam;
@Wachtwoord
private String paswoord;
@Wachtwoord
private String paswoord2;
}
