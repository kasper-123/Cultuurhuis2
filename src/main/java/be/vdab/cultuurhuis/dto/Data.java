package be.vdab.cultuurhuis.dto;
import be.vdab.cultuurhuis.domain.Klant;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Data {

@JsonProperty("voorNaam")
private String voornaam;
@JsonProperty("familieNaam")
private String familieNaam;
@JsonProperty("straat")
private String straat;
@JsonProperty("huisnr")
private int huisnr;
@JsonProperty("postcode")
private int postcode;
@JsonProperty("gemeente")
private String gemeente;

public String getGebruikersnaam() {
	return gebruikersnaam;
}

@JsonProperty("gebruikersnaam")
private String gebruikersnaam;

public String getVoornaam() {
	return voornaam;
}

public String getFamilieNaam() {
	return familieNaam;
}

public String getStraat() {
	return straat;
}

public int getHuisnr() {
	return huisnr;
}

public int getPostcode() {
	return postcode;
}

public String getGemeente() {
	return gemeente;
}


}
