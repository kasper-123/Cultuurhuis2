package be.vdab.cultuurhuis.domain;


import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Embeddable
@Access(AccessType.FIELD)
public class Reservatie{
		@ManyToOne(fetch = FetchType.LAZY,optional = false)
		@JoinColumn
		private Voorstelling voorstelling;
		
		@Positive
		int plaatsen;
		
		 public Reservatie( Voorstelling voorstelling, int plaatsen) {

		 this.voorstelling = voorstelling;
		 this.plaatsen = plaatsen;
		 }
	
		protected Reservatie(){}

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
 Reservatie reservatie = (Reservatie) o;
 if((reservatie.getVoorstelling() ==this.voorstelling)){
 return true;
 }
 return false;
 }
 
 
 @Override
 public int hashCode() {
 return Objects.hash(voorstelling, plaatsen);
 }
}