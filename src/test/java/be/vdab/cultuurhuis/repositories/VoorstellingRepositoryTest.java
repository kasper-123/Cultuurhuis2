package be.vdab.cultuurhuis.repositories;

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
void verlaaagvooorstelloing() throws VoorstellingNietGevondenException {
	try {
		var x = voorstellingRepository.findById(2L).get();
		System.out.println(x.getTitel() + "   " + x.getVrijeplaatsen());
		assertThat(voorstellingRepository.boeking(2L, 4)).isEqualTo(1);
		System.out.println("plek  :" + voorstellingRepository.findById(2l).get().getVrijeplaatsen());
		System.out.println(voorstellingRepository.boeking(2L, 5));
		System.out.println(voorstellingRepository.findById(x.getId()).get().getVrijeplaatsen());
		var y = voorstellingRepository.findById(2L).get().getVrijeplaatsen();
		assertThat(x.getVrijeplaatsen()).isGreaterThan(y);
	} catch (VoorstellingNietGevondenException ex) {
		System.out.println(ex);
	}
}


@Test
void verlagenMetNIETteveelreservaties(){

var	x =voorstellingRepository.findById(2l).get().getVrijeplaatsen();
voorstellingRepository.boeking(2L,10);
	assertThat(voorstellingRepository.findById(2l).get().getVrijeplaatsen()).isLessThan(x);
}

@Test
void verlagenMetNullofMinder() {
	int x = -3;
	try {
		var voorstelling = voorstellingRepository.findById(2l).get();
	
		voorstellingRepository.boeking(2l,-20);
		assertThat(voorstellingRepository.findById(2L).get().getVrijeplaatsen()==voorstelling.getVrijeplaatsen());
		
	} catch (IllegalArgumentException | ConstraintViolationException ex) {
		System.out.println(ex);
	}
	
	
}
}