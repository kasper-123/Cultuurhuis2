package be.vdab.cultuurhuis.restcontrollers;


import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.exceptions.KlantNietGevondenException;
import be.vdab.cultuurhuis.exceptions.ReservatieNietGemaaktException;
import be.vdab.cultuurhuis.exceptions.VoorstellingNietGevondenException;
import be.vdab.cultuurhuis.services.ReservatieService;
import be.vdab.cultuurhuis.services.VoorstellingService;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Transactional
@RestController
@RequestMapping("/reservatie")
@CrossOrigin(exposedHeaders = "Location")
@ExposesResourceFor(Reservatie.class)



public class ReservatieController {
//private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	private ReservatieService reservatieService;
	private VoorstellingService voorstellingService;

public ReservatieController(ReservatieService reservatieService, VoorstellingService voorstellingService) {
	this.reservatieService = reservatieService;
	this.voorstellingService = voorstellingService;
}

@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
		HttpStatus maakReservatie(@RequestBody @Valid Reservatie reservatie) {
	
	try {
		
		voorstellingService.createBoeking(reservatie.getVoorstelling().getId(), reservatie.getPlaatsen());
	}catch (Exception ex) {
		System.out.println("vooorstellingfout");
		throw new ReservatieNietGemaaktException(ex);
		}
	
	try {
		reservatieService.create(reservatie);
		return HttpStatus.CREATED;
	} catch (KlantNietGevondenException | VoorstellingNietGevondenException ex) {
	return HttpStatus.BAD_REQUEST;
	}
}
	
	@ExceptionHandler(MethodArgumentNotValidException.class )
	@ResponseStatus(value =HttpStatus.BAD_REQUEST)
	Map<String,String> foutRespons(MethodArgumentNotValidException ex){
		return ex.getBindingResult().getFieldErrors().stream()
 .collect(Collectors.toMap(FieldError::getField,
				FieldError::getDefaultMessage));
	}
	
}


