package be.vdab.cultuurhuis.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.*;


@DataJpaTest
class VoorstellingTest extends AbstractTransactionalJUnit4SpringContextTests {
private Validator validator;
private Klant klant;
private Reservatie reservatie;
private Voorstelling voorstelling;
private Genre genre;

 @BeforeEach
 void beforeEach() {
	 ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	 validator = factory.getValidator();
	 genre = new Genre("dsdv");
	 klant = new Klant("iho", "io", "hu", 11, 9000, "ge", "dd", "dds");
	 reservatie = new Reservatie(klant, 8, voorstelling);
	 voorstelling = new Voorstelling("nui", "yio", LocalDate.now(), genre, BigDecimal.ONE, 200);
	
 }

 @Test
void voorstellingCorrect(){
 assertThat(validator.validate(voorstelling)).isEmpty();
 }

@Test
void voorstellingInCorrect() {
var	voorstelling2 = new Voorstelling("", "yio", LocalDate.now(), genre, BigDecimal.ONE, 200);
	assertThat(validator.validate(voorstelling2)).isNotEmpty();
}


@Test
void voorstellingInCorrect3() {
	var	voorstelling2 = new Voorstelling("fe", "yio", LocalDate.now(), genre, BigDecimal.ONE, -200);
	assertThat(validator.validate(voorstelling2)).isNotEmpty();
}

@Test
void voorstellingverminder(){
	var	voorstelling2 = new Voorstelling("fe", "yio", LocalDate.now(), genre, BigDecimal.ONE, 200);
	voorstelling2.verminderVrijePlaatsen(50);
	assertThat(voorstelling2.getVrijeplaatsen()).isEqualTo(150);
}
@Test
void voorstellingverminderteveel(){
	var	voorstelling2 = new Voorstelling("fe", "yio", LocalDate.now(), genre, BigDecimal.ONE, 200);
	
	assertThat(voorstelling2.verminderVrijePlaatsen(250)).isFalse();
}

@Test
void voorstellingvermindernegatief(){
	var	voorstelling2 = new Voorstelling("fe", "yio", LocalDate.now(), genre, BigDecimal.ONE, 200);
	
	assertThatIllegalArgumentException().isThrownBy(() ->voorstelling2.verminderVrijePlaatsen(-250));
}

}