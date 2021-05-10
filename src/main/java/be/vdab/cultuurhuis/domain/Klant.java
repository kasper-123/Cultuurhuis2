package be.vdab.cultuurhuis.domain;

import be.vdab.cultuurhuis.constraints.NieuweKlant;
import be.vdab.cultuurhuis.constraints.TekstVak;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Entity
@Table(name = "klanten")
@NieuweKlant
public class Klant {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
long id;

@NotBlank
private String voorNaam;
@NotBlank
private String familieNaam;
@NotBlank
private String straat;
@Positive
private int huisNr;
@Positive
private int postCode;
@NotBlank
private String gemeente;
@TekstVak
private String  gebruikersnaam;
@NotBlank
private String paswoord;



/**
@ElementCollection
@CollectionTable(name ="reservaties",joinColumns = @JoinColumn(name = "klantid"))
@Column(name = "reservatie")
private Set<Reservatie> reservaties;

public Set<Reservatie> getReservaties(){
	return Collections.unmodifiableSet(reservaties);
}**/










protected Klant(){}
public Klant( String voorNaam, String familieNaam, String straat, int huisNr, int postCode, String gemeente, String gebruikersnaam, String paswoord) {
	this.voorNaam = voorNaam;
	this.familieNaam = familieNaam;
	this.straat = straat;
	this.huisNr = huisNr;
	this.postCode = postCode;
	this.gemeente = gemeente;
	this.gebruikersnaam = gebruikersnaam;
	this.paswoord = paswoord;
//	this.reservaties=new LinkedHashSet<>();
}

public long getId(){
	return id;
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

public String getPaswoord() {
	return paswoord;
}


@Override
public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o instanceof Klant)) return false;
	Klant klant = (Klant) o;
	return id == klant.id && Objects.equals(gebruikersnaam, klant.gebruikersnaam);
}

@Override
public int hashCode() {
	return Objects.hash(id, gebruikersnaam);
}
}