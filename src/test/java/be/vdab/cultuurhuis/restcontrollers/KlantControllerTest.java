package be.vdab.cultuurhuis.restcontrollers;


import be.vdab.cultuurhuis.exceptions.KlantNietGevondenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class KlantControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final MockMvc mvc;

    KlantControllerTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @BeforeEach
    void beforeEach(){
    }




    @Test
    void KlantLezen() throws Exception{
        mvc.perform(get("/klanten/{id}",1))
                .andExpect(status().isOk())
        .andExpect(jsonPath("voorNaam").value("Hans"));
    }



    @Test
    void KlantLezenonbestaand() throws Exception{
        System.out.println();
        try {
            mvc.perform(get("/klanten/{id}", -2));
        }catch (KlantNietGevondenException ex){
            System.out.println("klant niet gevonden");
        }


    }






}
