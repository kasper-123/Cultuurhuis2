package be.vdab.cultuurhuis.controllers;


import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

import be.vdab.cultuurhuis.repositories.VoorstellingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class MandjeControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
@Mock
private MockMvc mock;
	private final MandjeController mandjeController;
	private final VoorstellingRepository voorstellingRepository;

public MandjeControllerTest(MandjeController mandjeController, VoorstellingRepository voorstellingRepository, MockMvc mock) {
	this.mandjeController = mandjeController;
	this.voorstellingRepository = voorstellingRepository;
	this.mock=mock;
}
@Test
void addbestelling() throws Exception {
	mock.perform(get("/mandje")).andExpect(status().isOk());
	try {
		assertThat(mandjeController.add(2, 4)).isTrue();
	} catch (Exception exception) {
		throw new IllegalArgumentException();
	}
}
	@Test
	void addDubbeleBestelling(){
		try{
			mock.perform(get("/mandje/1/4")).andExpect(status().isOk());
			assertThat(mandjeController.add(2,4)).isTrue();
			assertThat(mandjeController.add(2,4)).isTrue();}
		catch (Exception exception){
			throw new IllegalArgumentException();
		}

}

@Test
void addeBestellingDanCLear(){
	try{
		assertThat(mandjeController.add(3,4)).isTrue();
		assertThat(mandjeController.add(2,4)).isTrue();
		assertThat(mandjeController.add(5,4)).isTrue();
	assertThat(mandjeController.getMandje().getModel().size()).isEqualTo(3);
	
	assertThat(mandjeController.mandje.clearMandje()).isTrue();
		assertThat(mandjeController.getMandje().getModel().size()).isEqualTo(0);
	}
	catch (Exception exception){
		throw new IllegalArgumentException();
	}
	
}
}