package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.restclients.UserClient;
import be.vdab.cultuurhuis.services.GenreService;
import be.vdab.cultuurhuis.services.ReservatieService;
import be.vdab.cultuurhuis.services.VoorstellingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Webconfig {

	
	@Bean
IndexController index(GenreService genreService){
		return  new IndexController(genreService);
	}

@Bean
UserController begin(UserClient client,ReservatieService reservatieService, VoorstellingService voorstellingService){
		return  new UserController(client, reservatieService, voorstellingService);
}
}
