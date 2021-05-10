package be.vdab.cultuurhuis.repositories;
import static org.assertj.core.api.Assertions.*;


import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.domain.Voorstelling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;
import java.time.LocalDate;

@DataJpaTest(showSql = false)
public class KlantRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	private KlantRepository  klantRepository;
private Klant klant;
private Reservatie reservatie;
private Voorstelling voorstelling;
private Genre genre;


@BeforeEach
void beforeEach(){
	genre=new Genre("dsdv");
	klant= new Klant("iho","io","hu",11,9000,"ge","dd","dds");
	reservatie = new Reservatie(klant,8,voorstelling);
	voorstelling= new Voorstelling("nui","yio", LocalDate.now(),genre, BigDecimal.ONE,200 );
	
}
public KlantRepositoryTest(KlantRepository klantRepository) {
	this.klantRepository = klantRepository;
}

private long idVaKLant() {
	return jdbcTemplate.queryForObject(
			"select id from klanten where gebruikersnaam = 'hans'", Long.class);
}

@Test
void findByGebruikersnaam(){
		assertThat(klantRepository.findByGebruikersnaam("hans")).isNotEmpty();
assertThat(klantRepository.findByGebruikersnaam("hans")).isEqualTo(klantRepository.findById(idVaKLant()));
	
}
@Test
void findByGebruikersnaamBestaatnie(){
	assertThat(klantRepository.findByGebruikersnaam("°°°")).isEmpty();
	
}


}
