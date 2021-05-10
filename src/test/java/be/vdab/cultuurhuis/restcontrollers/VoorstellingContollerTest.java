package be.vdab.cultuurhuis.restcontrollers;


import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.domain.Voorstelling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VoorstellingContollerTest extends AbstractTransactionalJUnit4SpringContextTests {
private final MockMvc mvc;

private Klant klant;
private Reservatie reservatie;
private Voorstelling voorstelling;
private Genre genre;


@BeforeEach
void beforeEach() {
    genre = new Genre("dsdv");
    klant = new Klant("iho", "io", "hu", 11, 9000, "ge", "dd", "dds");
    reservatie = new Reservatie(klant,3,voorstelling);
    voorstelling = new Voorstelling("nui", "yio", LocalDate.now(), genre,  BigDecimal.ONE, 200);
    
}



private String titelVanVoorstelling1() {
    return jdbcTemplate.queryForObject("select titel from voorstellingen where id=1", String.class);
}

VoorstellingContollerTest(MockMvc mvc) {
    this.mvc = mvc;
}

@Test
void findOnbestaandID() throws Exception {
    mvc.perform(get("/voorstellingen/{id}", -1))
            .andExpect(status().isNotFound());
}

@Test
void findById() throws Exception {
    mvc.perform(get("/voorstellingen/{id}", 1))
            .andExpect(status().isOk())
            .andExpect(jsonPath("titel").value(titelVanVoorstelling1()));
}

@Test
void ticketkopenNuLL(){
    reservatie = new Reservatie(null,8,voorstelling);
    try {
        mvc.perform(post("/klanten/{id}",1,reservatie))
                .andExpect(status().isBadRequest());
        
        
    } catch (Exception e) {
        throw    new RuntimeException();
        
    }
    
}
@Test
void ticketkopenklant(){
    
    try {
        mvc.perform(post("/klanten/{id}",-1,reservatie))
                .andExpect(status().isBadRequest());
        
        
    } catch (Exception e) {
        throw    new RuntimeException();
        
    }
}



@Test
void findBysIdenVERMINDERD() throws Exception {
    var aantal=2;

    mvc.perform(put("/voorstellingen/{id}", 3, voorstelling)
                        .contentType(MediaType.APPLICATION_JSON)
            .content("2"));

    System.out.println(voorstelling);
    
}

}
