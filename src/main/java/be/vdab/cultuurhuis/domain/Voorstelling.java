package be.vdab.cultuurhuis.domain;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "voorstellingen")
public class Voorstelling {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

@NotBlank
private String titel;
@NotBlank
private String uitvoerders;
@DateTimeFormat
private LocalDate datum;

@ManyToOne
@JoinColumn(name="genreid")
private Genre genre;



 
 private boolean addReservatie(Reservatie reservatie){
 	
 	if (vrijeplaatsen> reservatie.getPlaatsen()){
 		addReservatie(reservatie);
 		return true;
    }
 	
 	
 	
 	return false;
 }






@PositiveOrZero
@Digits(integer = 7 ,fraction = 2)
private BigDecimal prijs;

@PositiveOrZero
int vrijeplaatsen;


public Voorstelling(String titel, String uitvoerders, LocalDate datum, Genre genre, BigDecimal prijs, int vrijeplaatsen) {
	this.titel = titel;
	this.uitvoerders = uitvoerders;
	this.datum = datum;
	this.genre = genre;
	
	this.prijs = prijs;
	this.vrijeplaatsen = vrijeplaatsen;

}
protected Voorstelling(){

}

public long getId() {
	return id;
}

public String getTitel() {
	return titel;
}

public String getUitvoerders() {
	return uitvoerders;
}

public LocalDate getDatum() {
	return datum;
}

public BigDecimal getPrijs() {
	return prijs;
}

public int getVrijeplaatsen() {
	return vrijeplaatsen;
}





@Override
public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o instanceof Voorstelling)) return false;
	Voorstelling that = (Voorstelling) o;
	return id == that.id && titel.equals(that.titel) && uitvoerders.equals(that.uitvoerders) && datum.equals(that.datum) && genre.equals(that.genre) && prijs.equals(that.prijs);
}

@Override
public int hashCode() {
	return Objects.hash(id, titel, uitvoerders);
}

public boolean verminderVrijePlaatsen(int aantal){
	
	if (aantal<=0){
		throw new IllegalArgumentException();
	}
	if(aantal<=vrijeplaatsen){
		vrijeplaatsen=vrijeplaatsen-aantal;
		return true;
	}
	
	return false;
}


public Voorstelling metId(long id){
	var voorstellingMetId= new Voorstelling(titel,uitvoerders,datum,genre, prijs,vrijeplaatsen);
	voorstellingMetId.id=id;
	return voorstellingMetId;
	
}

@Override
public String toString() {
	return "Voorstelling{" +
			       "id=" + id +
			       ", titel='" + titel + '\'' +
			       ", uitvoerders='" + uitvoerders + '\'' +
			       ", datum=" + datum +
			       ", genre=" + genre +
			       ", prijs=" + prijs +
			       ", vrijeplaatsen=" + vrijeplaatsen +
			       '}';
}
}
