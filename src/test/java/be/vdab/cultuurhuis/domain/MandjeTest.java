package be.vdab.cultuurhuis.domain;

import be.vdab.cultuurhuis.dto.Mandje;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

@DataJpaTest
public class MandjeTest extends AbstractTransactionalJUnit4SpringContextTests {
private final Mandje mandje= new Mandje();

public Mandje getMandje() {
	return mandje;
}
@Test
void nieuwMandjeisLeeg() throws Exception {
	assertThatNullPointerException().isThrownBy(()->mandje.getMandje());
}
@Test void nadatJeEenItemInHetMandjeLegtBevatDitMandjeEnkelDitItem() throws Exception {
	mandje.add(1L,3);
	assertThat(mandje.getMandje().size()).isEqualTo(1); }

@Test void kanGeen2DezelfdeItemsToevoegen() throws Exception {
	mandje.add(1L,3);
	mandje.add(1L,3);
	System.out.println(mandje.getMandje().toString());
	assertThat(mandje.getMandje().size()).isEqualTo(1); }

@Test
void TweeItemsToevoegen() throws Exception {
	mandje.add(1L,3);
	mandje.add(2L,3);
	System.out.println(mandje.getMandje().size());
	assertThat(mandje.getMandje().size()).isEqualTo(2); }
@Test
void clear() throws Exception {
	mandje.add(1L, 3);
	mandje.add(2L, 3);
	System.out.println(mandje.getMandje().size());
	assertThat(mandje.getMandje().size()).isEqualTo(2);
	mandje.clearMandje();
	assertThatNullPointerException().isThrownBy(()->mandje.getMandje());
	
}


}
