package be.vdab.cultuurhuis.controllers;


import be.vdab.cultuurhuis.restclients.UserClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
private final MockMvc mockMvc;
private final	UserController userController;
private final UserClient userClient;

	
	public UserControllerTest(MockMvc mockMvc, UserController userController, UserClient userClient) {
	this.mockMvc = mockMvc;
	this.userController = userController;
	this.userClient = userClient;
		SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken("eenGebruikersNaam", null, null));
}






@Test
void getUser() throws Exception {
mockMvc.perform(get("/klanten/1")).andExpect(status().isOk())	;
	mockMvc.perform(get("/klanten/4")).andExpect(status().isOk())	;

}



@Test
void toevoegenMandje(long id, int aantal) throws Exception {
	

	assertThat(userController.addMandje(2L,4)).isTrue();
	assertThat(userController.addMandje(3,5)).isTrue();
	 mockMvc.perform(post("/klanten/{id}/{aantal}",1,9)).andExpect(status().isOk());

	
	mockMvc.perform(get("/users/{id}",5)).andExpect(status().isOk());
	
	
	mockMvc.perform(get("/users/mandje/{id}/{aantal}",6,2)).andExpect(status().isOk());
	
}

}
