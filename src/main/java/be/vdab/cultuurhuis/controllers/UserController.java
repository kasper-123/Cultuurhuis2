package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.dto.Mandje;
import be.vdab.cultuurhuis.exceptions.KlantNietGevondenException;
import be.vdab.cultuurhuis.exceptions.ReservatieNietGemaaktException;
import be.vdab.cultuurhuis.exceptions.VoorstellingNietGevondenException;
import be.vdab.cultuurhuis.restclients.UserClient;
import be.vdab.cultuurhuis.services.ReservatieService;
import be.vdab.cultuurhuis.services.VoorstellingService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;

@SessionScope
@RequestMapping("users")
class UserController implements Serializable {
private final UserClient client;
private ReservatieService reservatieService;
private Mandje mandje = new Mandje();
private Klant klant;
private final VoorstellingService voorstellingService;

UserController(UserClient client, ReservatieService reservatieService, VoorstellingService voorstellingService) {
	SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("eenGebruikersNaam", null, null));
	
	this.client = client;
	this.reservatieService = reservatieService;
	this.voorstellingService = voorstellingService;
}
@GetMapping("{id}")
public ModelAndView getUser(@PathVariable long id) {
	
	var modelAndView = new ModelAndView("user");
	client.findById(id).ifPresent(user -> modelAndView.addObject(user));
	return modelAndView;
	
}


@RequestMapping("/mandje/{id}/{aantal}")
boolean addMandje(@PathVariable long id,@PathVariable int aantal) {
	try {
		
		for (var e : mandje.getMandje().entrySet()) {
			if(
			e.getKey()==id){
				e.setValue(aantal);
			}
			else mandje.add(id, aantal);
			
		}
	
		return true;
	} catch (Exception exception) {
		exception.printStackTrace();
	}
	return false;
	
}
	@GetMapping("{id}/bestellen")
ModelAndView bestelling(@PathVariable long id) throws Exception {
	var modelAndView = new ModelAndView("overzicht");
	try {
		Klant klant = client.findById(id).get();
		
		
		try {//zoek de mand en throw exception indien leeg
			var mand = mandje.getMandje();
			
			for (var e : mand.entrySet()) {
				try {
					Voorstelling voorstelling = client.getdataVoostellingen(e.getKey()).get();
					try {
						reservatieService.create(new Reservatie(klant, e.getValue(), voorstelling));
						System.out.println("SUCCESSS");
						modelAndView.addObject("gelukt", e);
					} catch (ReservatieNietGemaaktException ex) {
						System.out.println("MISLUKT");
						modelAndView.addObject("mislukt", e);
						throw new ReservatieNietGemaaktException(ex);
					}
					
					
				} catch (VoorstellingNietGevondenException ex) {
					System.out.println("voorstelling niet gevonden");
					throw new NullPointerException();
				}
				
			}
			return modelAndView;
		} catch (NullPointerException ex) {
			System.out.println("mandje is leeg");
			
		}
		
		
	} catch (KlantNietGevondenException ex) {
		throw new KlantNietGevondenException();
	}
	return new ModelAndView("index","fouten","foute ");
}



}

