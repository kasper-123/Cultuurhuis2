package be.vdab.cultuurhuis.repositories;

import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.exceptions.ReservatieNietGemaaktException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.time.LocalDate;

@DataJpaTest
public class ReservatieRespositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
private final ReservatieRepository reservatieRepository;
private final VoorstellingRepository voorstellingRepository;
private  final KlantRepository klantRepository;
private Validator validator;
private Klant klant;
private Reservatie reservatie;
private Voorstelling voorstelling;
private Genre genre;

public ReservatieRespositoryTest(ReservatieRepository reservatieRepository, VoorstellingRepository voorstellingRepository, KlantRepository klantRepository) {
	this.reservatieRepository = reservatieRepository;
	this.voorstellingRepository = voorstellingRepository;
	this.klantRepository = klantRepository;
}

@BeforeEach
void beforeEach() {
klant=klantRepository.findByGebruikersnaam("hans").get();
	voorstelling = voorstellingRepository.findById(2L).get();
		reservatie = new Reservatie(klant, 8, voorstelling);
}


@Test
void maakReservatie() {
	System.out.println(reservatie.getVoorstelling().getVrijeplaatsen() +" en aantal plaatsen"+reservatie.getPlaatsen());
	var plaatsen= voorstelling.getVrijeplaatsen();
	try {
		reservatieRepository.save(reservatie);
		
	}catch (ReservatieNietGemaaktException ex){
		throw  new ReservatieNietGemaaktException(ex);
	}
	

	
}

/**
@Test
void maakDubbeleReservatie() {
	reservatieRepository.save(reservatie);

assertThat(reservatieRepository.save(new Reservatie(klant,8,voorstelling))))
	assertThat(countRowsInTable("reservaties")).isGreaterThan(aantal);
	
	reservatieRepository.save(reservatie);
	assertThat(countRowsInTable("reservaties")).isEqualTo(aantal+2);
	
	
}

@Test
void maakOngeldigereservatieKlant() {
	klant = new Klant("iho", "io", "hu", -11, 9000, "ge", "dd", "ddis");
	
	var reservatie2=new Reservatie(klant,3,voorstelling);
	var aantal = countRowsInTable("reservaties");
assertThat(reservatieRepository.save(reservatie2));

	assertThat(countRowsInTable("reservaties")).isEqualTo(aantal);
	
	reservatieRepository.save(reservatie2);
	assertThat(countRowsInTable("reservaties")).isEqualTo(aantal);
}

@Test
void maakOngeldigereservatieVoorstelling() {
	voorstelling = new Voorstelling("nui", "yio", LocalDate.now(), genre, BigDecimal.TEN, -200);

	var reservatie2=new Reservatie(klant,3,voorstelling);
	var aantal = countRowsInTable("reservaties");
	assertThat(reservatieRepository.save(reservatie2));
	
	assertThat(countRowsInTable("reservaties")).isEqualTo(aantal);
	
	reservatieRepository.save(reservatie2);
	assertThat(countRowsInTable("reservaties")).isEqualTo(aantal);
}**/

}
