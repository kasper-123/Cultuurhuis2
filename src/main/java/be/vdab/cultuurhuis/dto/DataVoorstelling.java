package be.vdab.cultuurhuis.dto;

import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.domain.Reservatie;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DataVoorstelling {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

@JsonProperty("titel")
private String titel;
@JsonProperty("uitvoerders")
private String uitvoerders;
@JsonProperty("datum")
private LocalDate datum;
@JsonProperty("genre")
private Genre genre;
@JsonProperty("vrijePlaatsen")
private int vrijePlaatsen;
@JsonProperty("prijs")
private BigDecimal prijs;

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

public Genre getGenre() {
	return genre;
}

public int getVrijePlaatsen() {
	return vrijePlaatsen;
}

public BigDecimal getPrijs() {
	return prijs;
}

public int getVrijeplaatsen() {
	return vrijeplaatsen;
}

@PositiveOrZero
int vrijeplaatsen;

}
