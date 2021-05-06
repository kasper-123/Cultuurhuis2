package be.vdab.cultuurhuis.restcontrollers;


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
class VoorstellingContollerTest extends AbstractTransactionalJUnit4SpringContextTests {
    private  final MockMvc mvc;

    private long idVanTestVoorstelling(){
        return jdbcTemplate.queryForObject("select id from voorstellingen where naam='kasper'",Long.class);
    }
    private String titelVanVoorstelling1(){
        return jdbcTemplate.queryForObject("select titel from voorstellingen where id=1",String.class);
    }

    VoorstellingContollerTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    void findOnbestaandID()throws  Exception{
mvc.perform(get("/voorstellingen/{id}",-1))
        .andExpect(status().isNotFound());
    }

    @Test
    void findById()throws Exception{
        mvc.perform(get("/voorstellingen/{id}",1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("titel").value(titelVanVoorstelling1()));
    }


}
