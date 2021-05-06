package be.vdab.cultuurhuis.restcontrollers;


import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.exceptions.KlantNietGevondenException;
import be.vdab.cultuurhuis.services.KlantService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.TypedEntityLinks;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("klanten")
@CrossOrigin(exposedHeaders ="Location")
@ExposesResourceFor(Klant.class)

public class KlantController {
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


    @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
    HttpHeaders create(@RequestBody @Valid Klant klant){
klantService.create(klant);
var headers = new HttpHeaders();
headers.setLocation(links.linkToItemResource(klant).toUri());
return headers;
    }
    /**
@PostMapping("{id})")
@ResponseStatus(HttpStatus.CREATED)
HttpHeaders addReservatie(@RequestBody @Valid Reservatie reservatie,@PathVariable long id){
    var klantService.findById(id)). ;
}**/



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
            this.reservaties=klant.getReservaties();
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