package be.vdab.cultuurhuis.restcontrollers;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

class GenreControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final MockMvc mvc;

    GenreControllerTest(MockMvc mvc) {
        this.mvc = mvc;
    }

private long idVanTestGenre() {
        return jdbcTemplate.queryForObject(
        "select id from genres where naam = 'kasper'", Long.class);
        }

@Test
    void genreLezen() throws Exception{
        
        long id= idVanTestGenre();
    System.out.println(id);
        mvc.perform(get("/genres/{id}",id))
                .andExpect(status().isOk());
}
@Test
    void onbestaanGenreLezen() throws Exception{

        mvc.perform(get("/genres/{id}",-1))
                .andExpect(status().isNotFound());
}

}