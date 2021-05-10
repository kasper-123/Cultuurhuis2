package be.vdab.cultuurhuis.restcontrollers;


import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.exceptions.ReservatieNietGemaaktException;
import be.vdab.cultuurhuis.repositories.KlantRepository;
import be.vdab.cultuurhuis.repositories.ReservatieRepository;
import be.vdab.cultuurhuis.repositories.VoorstellingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.JsonString;
import io.swagger.v3.core.util.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONParser;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
public class ReservatieControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
private final MockMvc mvc;
private Klant klant;
private Reservatie reservatie;
private Voorstelling voorstelling;
private Genre genre;
private KlantRepository klantRepository;
private ReservatieRepository reservatieRepository;
private VoorstellingRepository voorstellingRepository;

@BeforeEach
void beforeEach() {
	//genre=new Genre("dsdv");
	klant = klantRepository.findByGebruikersnaam("hans").get();
	
	voorstelling = voorstellingRepository.findById(2l).get();
	reservatie = new Reservatie(klant, 8, voorstelling);
}

public ReservatieControllerTest(MockMvc mvc, ReservatieRepository reservatieRepository, KlantRepository klantRepository, VoorstellingRepository voorstellingRepository) {
	this.mvc = mvc;
	this.reservatieRepository = reservatieRepository;
	this.klantRepository = klantRepository;
	this.voorstellingRepository = voorstellingRepository;
}

@Test
void nieuweReservatie() throws Exception {
	try {
		long c = 2;
		var klant2 = klantRepository.findByGebruikersnaam("hans").get();
		var voorstelling2 = voorstellingRepository.findById(c).get();
		var reservatie3 = new Reservatie(klant2, 3, voorstelling2);
		mvc.perform(post("/reservatie").content(
				asJsonString(reservatie3)).contentType(MediaType.APPLICATION_JSON)
				            .accept(MediaType.APPLICATION_JSON)
		).andExpect(status().isCreated());
	} catch (ReservatieNietGemaaktException ex) {
		throw new ReservatieNietGemaaktException(ex);
	}
}
	
	@Test
	void nieuweReservatieKanGeen2Keer () throws Exception {
		mvc.perform(post("/reservatie").
				                               content(asJsonString(reservatie)).contentType(MediaType.APPLICATION_JSON)
				            .accept(MediaType.APPLICATION_JSON)
		).andExpect(status().isCreated());
		
		mvc.perform(post("/reservatie", reservatie).
				                                           content(asJsonString(null)).contentType(MediaType.APPLICATION_JSON)
				            .accept(MediaType.APPLICATION_JSON)
		).andExpect(status().isBadRequest());
		
	}
	
	@Test
	void nieuweReservatieNull () throws Exception {
		
		
		mvc.perform(post("/reservatie")
				            .content(asJsonString(null)).contentType(MediaType.APPLICATION_JSON)
				            .accept(MediaType.APPLICATION_JSON)
		).andExpect(status().isBadRequest());
	}
	@Test
	void Onggeldigereservatie ()throws Exception {
		mvc.perform(post("/reservatie")
				            .content(asJsonString(null)).contentType(MediaType.APPLICATION_JSON)
				            .accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	public static String asJsonString ( final Object obj){
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
}
