package be.vdab.cultuurhuis.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Data {
@JsonProperty("id")
private long id;
@JsonProperty("first_name")
private String firstName;
@JsonProperty("last_name")
private String lastName;
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

public long getId() {
	return id;
}

public String getFirstName() {
	return firstName;
}

public String getLastName() {
	return lastName;
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
