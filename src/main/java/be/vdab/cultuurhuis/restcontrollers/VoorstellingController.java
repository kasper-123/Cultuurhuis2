package be.vdab.cultuurhuis.restcontrollers;


import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.exceptions.VoorstellingNietGevondenException;
import be.vdab.cultuurhuis.repositories.VoorstellingRepository;
import be.vdab.cultuurhuis.services.VoorstellingService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.TypedEntityLinks;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("voorstellingen")
@CrossOrigin(exposedHeaders = "Location")
@ExposesResourceFor(Voorstelling.class)
public class VoorstellingController {
    //TODO  aantal plaatsen verminderen verminderen met
// x maar enkel dat en nie onder 0
// en



    
    
    
    
    private final VoorstellingService voorstellingService;
    private final TypedEntityLinks.ExtendedTypedEntityLinks<Voorstelling> links;
    public VoorstellingController(VoorstellingService voorstellingService, EntityLinks links) {
        this.voorstellingService = voorstellingService;
        this.links=links.forType(Voorstelling.class,Voorstelling::getId);
    }

@GetMapping
List<Voorstelling> findAll(){
       return voorstellingService.findAll();
}

    @GetMapping("{id}")
    EntityModel<Voorstelling> getById(@PathVariable long id){
      try {
         return EntityModel.of(voorstellingService.findbyid(id));
      }catch (VoorstellingNietGevondenException ex){
          throw  new VoorstellingNietGevondenException();
      }

    }
    
    
    /**
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
   boolean boekinPlaatsen(@RequestBody @Valid Voorstelling voorstelling) throws Exception{
        if (voorstelling.getVrijeplaatsen() > 20) {
            voorstellingService.createBoeking(voorstelling);
            return true;
        }else throw new VoorstellingRepository().
    return true;
    }
**/

    @ExceptionHandler(VoorstellingNietGevondenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void genreNietGevonden(){}





}
