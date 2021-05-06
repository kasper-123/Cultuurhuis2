package be.vdab.cultuurhuis.domain;


import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

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
private LocalDate datum;

@ManyToOne
@JoinColumn(name="genreid")
private Genre genre;

/**
 @OneToMany
 @JoinColumn
 private final Set<Voorstelling> voorstellingen;
 public Set<Voorstelling> getVoorstellingen() {
 return voorstellingen;
 }**/

@PositiveOrZero
@Digits(integer = 7 ,fraction = 2)
private BigDecimal prijs;

int vrijeplaatsen;


public Voorstelling(String titel, String uitvoerders, LocalDate datum, Genre genre, BigDecimal prijs, int vrijeplaatsen) {
	this.titel = titel;
	this.uitvoerders = uitvoerders;
	this.datum = datum;
	this.genre = genre;
	this.prijs = prijs;
	this.vrijeplaatsen = vrijeplaatsen;
	// this.voorstellingen=new LinkedHashSet<>();
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
	
	if (o instanceof Voorstelling){
		return ((Voorstelling) o).id == this.id;
	}
	return false;
}

@Override
public int hashCode() {
	return Objects.hash(id, titel, uitvoerders);
}
}
