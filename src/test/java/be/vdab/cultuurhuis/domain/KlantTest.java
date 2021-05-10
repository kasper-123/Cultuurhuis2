package be.vdab.cultuurhuis.domain;
import be.vdab.cultuurhuis.exceptions.KlantBestaatAlException;
import be.vdab.cultuurhuis.repositories.KlantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import static org.assertj.core.api.Assertions.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;


@DataJpaTest
public class KlantTest extends AbstractTransactionalJUnit4SpringContextTests {
private Validator validator;
private Klant klant;
private Reservatie reservatie;
private Voorstelling voorstelling;
private Genre genre;
KlantRepository klantRepository;

public KlantTest(KlantRepository klantRepository) {
	this.klantRepository=klantRepository;
}

@BeforeEach
void beforeEach() {
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	validator =  factory.getValidator();
	genre = new Genre("dsdv");
	klant = new Klant("iho", "io", "hu", 11, 9000, "ge", "dd", "dds");
	reservatie = new Reservatie(klant, 8, voorstelling);
	voorstelling = new Voorstelling("nui", "yio", LocalDate.now(), genre, BigDecimal.ONE, 200);
	
}


@Test
void KlantCorrect() throws Exception {
	assertThat(validator.validate(klant)).isEmpty();
}

@Test
		void KlantOngeldigVeldenOntbreken() {
	var klant2 = new Klant("", "io", "hu", 11, 9000, "ge", "dd", "dds");
	assertThat(validator.validate(klant2)).isNotEmpty();
	var klant3 = new Klant("iho", "", "hu", 11, 9000, "ge", "dd", "dds");
	assertThat(validator.validate(klant3)).isNotEmpty();
	var klant4 = new Klant("iho", "io", "", 11, 9000, "ge", "dd", "dds");
	assertThat(validator.validate(klant4)).isNotEmpty();
	var klant5 = new Klant("iho", "io", "hu", -2, 9000, "ge", "dd", "dds");
	assertThat(validator.validate(klant5)).isNotEmpty();
	var klant6 = new Klant("iho", "io", "hu", 11, 9000, "", "dd", "dds");
	assertThat(validator.validate(klant6)).isNotEmpty();
	
}



}



