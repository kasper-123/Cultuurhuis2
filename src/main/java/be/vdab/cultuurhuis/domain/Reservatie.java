package be.vdab.cultuurhuis.domain;


import be.vdab.cultuurhuis.constraints.BeschikbaarReserveer;
import be.vdab.cultuurhuis.constraints.Getal;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "reservaties")
@BeschikbaarReserveer
public class Reservatie implements Serializable {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "klantId")
@Valid
private Klant klant;

@ManyToOne(optional = false,fetch = FetchType.LAZY)
@JoinColumn(name = "voorstellingid")
private Voorstelling voorstelling;


@Getal
int plaatsen;

public Reservatie(Klant klant, int plaatsen, Voorstelling voorstelling) {
	this.voorstelling=voorstelling;
	this.klant = klant;
	this.plaatsen = plaatsen;
}

protected Reservatie() {
}

public long getId() {
	return id;
}

public Klant getKlant() {
	return klant;
}

public Voorstelling getVoorstelling() {
	return voorstelling;
}

public int getPlaatsen() {
	return plaatsen;
}

@Override
public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o instanceof Reservatie)) return false;
	Reservatie that = (Reservatie) o;
	return plaatsen == that.plaatsen && Objects.equals(klant, that.klant);
}

@Override
public int hashCode() {
	return Objects.hash(klant, plaatsen);
}

}
 

 
