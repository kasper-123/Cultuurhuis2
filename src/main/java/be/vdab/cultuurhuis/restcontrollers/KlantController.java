package be.vdab.cultuurhuis.restcontrollers;


import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.exceptions.KlantNietGevondenException;
import be.vdab.cultuurhuis.exceptions.KlantNietgemaaktException;
import be.vdab.cultuurhuis.forms.NieuweKlantForm;
import be.vdab.cultuurhuis.services.KlantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.TypedEntityLinks;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("klanten")
@CrossOrigin(exposedHeaders ="Location")
@ExposesResourceFor(Klant.class)

public class KlantController {
private final Logger logger = LoggerFactory.getLogger(this.getClass());
private final KlantService klantService;
private final TypedEntityLinks.ExtendedTypedEntityLinks<Klant> links;

    public KlantController(KlantService klantService, EntityLinks links) {
        this.klantService = klantService;
        this.links = links.forType(Klant.class,Klant::getId);
    }


    @GetMapping("{id}")
    public EntityModel<KlantInfo> getKlant(@PathVariable long id) {
        return klantService.findById(id).map(klant ->
                EntityModel.of(new KlantInfo(klant))).orElseThrow(KlantNietGevondenException::new);
    }
@GetMapping("klantform")
public EntityModel<Klant> Registratie(){
        return EntityModel.of(new Klant("","","",0,0,"","",""));

}

    @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   HttpHeaders create(@RequestBody @Valid NieuweKlantForm klant) {
    if(klant.getPaswoord().equals(klant.getPaswoord2())){
        var k=new Klant(klant.getVoorNaam(), klant.getFamilieNaam(),
                klant.getStraat(), klant.getHuisNr(), klant.getPostCode(),
                klant.getGemeente(),  klant.getGebruikersnaam(), klant.getPaswoord() );
    
        klantService.create(k);
        var headers = new HttpHeaders();
        headers.setLocation(links.linkToItemResource(k).toUri());
        return headers;
    
    
    }throw new KlantNietgemaaktException();
    }

/**
@PostMapping("klanten/{id}/")
@ResponseStatus(HttpStatus.CREATED)
void addReservation(@RequestBody @Valid Reservatie reservatie,@PathVariable long id){
        klantService.boekReservatie(id,reservatie);
}

**/


@ExceptionHandler(KlantNietGevondenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void onbesstaandeklant(){}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String,String> verkeerdeData(MethodArgumentNotValidException ex){
        return ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField
                ,FieldError::getDefaultMessage));
    }

 



    private static class KlantInfo{
        String voorNaam;
        private String familieNaam;
        private String straat;
        private int huisNr;
        int postCode;
        private String gemeente;
        Set<Reservatie> reservaties;
        public KlantInfo(Klant klant) {
            this.voorNaam =klant.getVoorNaam();
            this.familieNaam =klant.getFamilieNaam();
            this.straat = klant.getStraat();
            this.huisNr = klant.getHuisNr();
            this.postCode = klant.getPostCode();
            this.gemeente =klant.getGemeente();
        }

        public String getVoorNaam() {
            return voorNaam;
        }

        public String getFamilieNaam() {
            return familieNaam;
        }

        public String getStraat() {
            return straat;
        }

        public int getHuisNr() {
            return huisNr;
        }

        public int getPostCode() {
            return postCode;
        }

        public String getGemeente() {
            return gemeente;
        }
    }


}