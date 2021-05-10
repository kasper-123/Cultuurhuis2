package be.vdab.cultuurhuis.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import static org.assertj.core.api.Assertions.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.time.LocalDate;

@DataJpaTest
public class ReservatieTest extends AbstractTransactionalJUnit4SpringContextTests {
private Validator validator;
private Klant klant;
private Reservatie reservatie;
private Voorstelling voorstelling;
private Genre genre;




@BeforeEach
void beforeEach() {
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	validator =(Validator)  factory.getValidator();
klant = new Klant("iho", "io", "hu", 11, 9000, "ge", "dd", "ddis");

voorstelling = new Voorstelling("nui", "yio",LocalDate.now(), genre, BigDecimal.TEN, 200);
	reservatie = new Reservatie(klant, 8, voorstelling);
}

@Test
void reservatieCorrect(){
	assertThat(validator.validate(reservatie)).isEmpty();
}

@Test
void reservatieNietCorrect(){
	reservatie = new Reservatie(klant, -7, voorstelling);
	assertThat(validator.validate(reservatie)).isNotEmpty();
}

@Test
void reservatieNirtCorrect()throws Exception{
	
		
		
		var reservatie2 = new Reservatie(klant, 248, voorstelling);
	
assertThat(validator.validate(reservatie2)).isNotEmpty();
	
}
@Test
void ReservatieInCorrect2(){
	var reservatie2 = new Reservatie(klant, -248, voorstelling);
	
	assertThat(validator.validate(reservatie2)).isNotEmpty();
}



}
