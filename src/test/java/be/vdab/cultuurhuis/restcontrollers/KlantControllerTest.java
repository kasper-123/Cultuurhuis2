package be.vdab.cultuurhuis.restcontrollers;


import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.exceptions.KlantNietGevondenException;
import be.vdab.cultuurhuis.repositories.KlantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class KlantControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final MockMvc mvc;

private Klant klant;
private Reservatie reservatie;
private Voorstelling voorstelling;
private Genre genre;
private KlantRepository klantRepository;


public KlantControllerTest(MockMvc mvc, KlantRepository klantRepository) {
    this.mvc = mvc;
    this.klantRepository = klantRepository;
}

@BeforeEach
    void beforeEach(){
        genre=new Genre("dsdv");
        klant= new Klant("iho","io","hu",11,9000,"ge","Add","dds");
        reservatie = new Reservatie(klant,8,voorstelling);
        voorstelling= new Voorstelling("nui","yio", LocalDate.now(),genre, BigDecimal.ONE,200 );
    
    }




    @Test
    void KlantLezen() throws Exception{
        mvc.perform(get("/klanten/{id}",1))
                .andExpect(status().isOk())
        .andExpect(jsonPath("voorNaam").value("Hans"));
    }

@Test
void KlantLezenOnbestaand2() throws Exception{
    mvc.perform(get("/klanten/{id}",-2))
            .andExpect(status().isNotFound());
}


@Test
void KantLezenOnbestaandNULL() throws Exception{
    mvc.perform(get("/klanten/(null)"));
    }

    @Test
    void KlantLezenonbestaand() throws Exception{
        System.out.println();
        try {
            mvc.perform(get("/klanten/{id}", -9));
        }catch (KlantNietGevondenException ex){
            System.out.println("klant niet gevonden");
        }
    }
    
    @Test
void maakNieuweKlant() throws Exception {
    var k=new Klant("iho","io","hu",11,9000,"ge","Adqqqqd","dds");
    
        mvc.perform(post("/klanten").content(
        asJsonString(k)).contentType(MediaType.APPLICATION_JSON)
                                          .accept(MediaType.APPLICATION_JSON)
	).andExpect(status().isCreated());
    
    }

@Test
void getUserFout() throws Exception {
    mvc.perform(get("/klanten/{id}",-2)).andExpect(status().isNotFound())	;
    mvc.perform(get("/klanten/{id}",1000)).andExpect(status().isNotFound())	;
    
}
    
    @Test
void maakNieuweKlantNULL() throws Exception {
    mvc.perform(post("/klanten", (Object) null))
            .andExpect(status().isBadRequest());
}

public static String asJsonString(final Object obj) {
    try {
        return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    
    
}


}
