package be.vdab.cultuurhuis.domain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
public class KlantTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	private Klant klant;
	private Reservatie reservatie;
private Voorstelling voorstelling;
private Genre genre;


	@BeforeEach
void beforeEach(){
		genre=new Genre("dsdv");
		klant= new Klant("iho","io","hu",11,9000,"ge","dd","dds");
		reservatie = new Reservatie(voorstelling,8);
		voorstelling= new Voorstelling("nui","yio", LocalDate.now(),genre, BigDecimal.ONE,200 );
		
	}




@Test
void klantKrijgXtraReservatieNULL() {

assertThatNullPointerException().isThrownBy(()->klant.addReservatie(null));
}

	
	
	@Test
void klantKrijgXtraReservatie() throws Exception {
	assertThat(klant.getReservaties()).isEmpty();
		System.out.println(klant);
		assertThat(klant.addReservatie(reservatie)).isTrue();
	assertThat(klant.getReservaties()).isNotEmpty();
	}
	


@Test
void KlantKrijgXtraReservatieDubbel() throws Exception {
	assertThat(klant.getReservaties()).isEmpty();
	assertThat(klant.addReservatie(reservatie)).isTrue();
	assertThat(klant.getReservaties()).isNotEmpty();
	assertThat(klant.addReservatie(reservatie)).isFalse();
	assertThat(klant.getReservaties()).containsOnly(reservatie);
}
}


