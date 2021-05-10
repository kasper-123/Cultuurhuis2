package be.vdab.cultuurhuis.restcontrollers;


import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.exceptions.GenreNietGevondenException;
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
    
    
    
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
   boolean boekinPlaatsen(@RequestBody @Valid long voorstellingId, int aantal) throws Exception {
        if (voorstellingService.findbyid(voorstellingId) != null) {
            if (voorstellingService.findbyid(voorstellingId).getVrijeplaatsen() >= aantal) {
                voorstellingService.createBoeking(voorstellingId, aantal);
                return true;
            } else throw new IllegalArgumentException("te weinig vrije plaatsen.");
    
    
        } else throw new VoorstellingNietGevondenException();
    }
    


    @ExceptionHandler(GenreNietGevondenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void genreNietGevonden(){}

@ExceptionHandler(VoorstellingNietGevondenException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
 void WeinigVrijePlaatsen(VoorstellingNietGevondenException ex){
    System.out.println(ex);
}




}
