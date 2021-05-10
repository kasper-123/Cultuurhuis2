package be.vdab.cultuurhuis.repositories;

import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.exceptions.VoorstellingNietGevondenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest(showSql = false)
class VoorstellingRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
private final VoorstellingRepository voorstellingRepository;


public VoorstellingRepositoryTest(VoorstellingRepository voorstellingRepository) {
	this.voorstellingRepository = voorstellingRepository;
}



@Test
void verlaaagvooorstelling() throws VoorstellingNietGevondenException {
	try {
		Voorstelling x;
		x = voorstellingRepository.findById(2L).get();
		System.out.println(x.getTitel() + "   " + x.getVrijeplaatsen());
	var y=	voorstellingRepository.findById(2L).get();
			y.verminderVrijePlaatsen(4);
		voorstellingRepository.save(y);
		voorstellingRepository.flush();
		
		assertThat(x.getVrijeplaatsen()).isGreaterThan(y.getVrijeplaatsen());

	} catch (VoorstellingNietGevondenException ex) {
		System.out.println(" voorstelling niet gevonden " +ex);
		throw new VoorstellingNietGevondenException();
	}
	
	try{
		var z=voorstellingRepository.findById(6L).get();
		System.out.println(z.getTitel() + "   " + z.getVrijeplaatsen());
		var y=	voorstellingRepository.findById(2l).get();
		y.verminderVrijePlaatsen(4);;
		voorstellingRepository.save(y);
		voorstellingRepository.flush();
		assertThat(z.getVrijeplaatsen()).isGreaterThan(y.getVrijeplaatsen()	);
		
	} catch (VoorstellingNietGevondenException ex) {
		System.out.println(ex);
	}
	}


@Test
void weBoekn10plaatsen(){
var x=	voorstellingRepository.findById(3l).get();
var	v= voorstellingRepository.findById(3l).get();
	v.verminderVrijePlaatsen(10);
	assertThat(v.getVrijeplaatsen()).isGreaterThan(x.getVrijeplaatsen());
}



@Test
void verlagenMetNIETteveelreservaties(){

var	x  =voorstellingRepository.getOne(2l);
	System.out.println( x.getVrijeplaatsen());
	System.out.println( x.verminderVrijePlaatsen(2));
	System.out.println( x.getVrijeplaatsen());
	
	voorstellingRepository.save(x);
	voorstellingRepository.flush();
	System.out.println(x);

}

@Test
void verlagenMetNullofMinder() {
	int x = -3;
	try {
		var voorstelling = voorstellingRepository.findById(2l).get();
	var y=voorstellingRepository.findById(4L).get();
	y.verminderVrijePlaatsen(x);
		assertThat(voorstellingRepository.findById(2L).get().getVrijeplaatsen()==voorstelling.getVrijeplaatsen());
		
	} catch (IllegalArgumentException | ConstraintViolationException ex) {
		System.out.println(ex);
	}
	
	
}
}