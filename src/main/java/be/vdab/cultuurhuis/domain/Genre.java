package be.vdab.cultuurhuis.domain;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="genres")
public class Genre {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private  long id;

@NotBlank
private    String naam;


@OneToMany
@JoinColumn(name = "genreid")
private Set<Voorstelling> voorstellingen;

public Set<Voorstelling> getVoorstellingen() {
	return Collections.unmodifiableSet(voorstellingen);
}

protected Genre(){}
public Genre(String naam) {
	this.naam = naam;
	this.voorstellingen=new LinkedHashSet<>();
}

public long getId() {
	return id;
}

public String getNaam() {
	return naam;
}

@Override
public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o instanceof Genre)) return false;
	Genre genre = (Genre) o;
	return this.id == getId();
}


@Override
public int hashCode() {
	return Objects.hash(id, naam, voorstellingen);
}
}
