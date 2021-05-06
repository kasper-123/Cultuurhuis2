package be.vdab.cultuurhuis.restcontrollers;

import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.exceptions.GenreNietGevondenException;
import be.vdab.cultuurhuis.services.GenreService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.TypedEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@RestController
@RequestMapping("genres")
@ExposesResourceFor(Genre.class)
@CrossOrigin(exposedHeaders = "Location")
public class GenreController {
private final GenreService genreService;
private final TypedEntityLinks<Genre>links;


    public GenreController(GenreService genreService, EntityLinks links) {
        this.genreService = genreService;
        this.links = links.forType(Genre.class,Genre::getId);
    }

    
    
    @GetMapping
    @Operation(summary = "alle genres zoeken")
    CollectionModel<EntityModel<GenreNaam>> findAll(){
        var genres=CollectionModel.of(
                genreService.findAll().stream()
                        .map(genre -> EntityModel.of(new GenreNaam(genre),
                                links.linkToItemResource(genre)))
                        ::iterator);
return genres;



    }


    @GetMapping("{id}")
    @Operation(summary = "voorstellingen op genre")
   EntityModel<GenreVoorstelling> get(@PathVariable long id){
        return EntityModel.of(new GenreVoorstelling(genreService.findById(id).orElseThrow(GenreNietGevondenException::new)));
    }

private static class GenreVoorstelling{
     private long id;
     private String naam;
    private Set<Voorstelling> voorstellingen = new LinkedHashSet<>();
    public Set<Voorstelling>getVoorstellingen(){

        return Collections.unmodifiableSet(voorstellingen);
    }

    public GenreVoorstelling(Genre genre) {
           this.id=genre.getId();
           this.naam=genre.getNaam();

        for (var i:genre.getVoorstellingen()){
        if (i.getDatum().isAfter(LocalDate.now())){
                this.voorstellingen.add(i);
            }else {
            System.out.println(i.getTitel());
        }
        }

    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}



            private static class GenreNaam{
        private String naam;
                public GenreNaam(Genre genre) {
                    this.naam = genre.getNaam();
                }
                public String getNaam() {
                    return naam;
                }
            }


    @ExceptionHandler(GenreNietGevondenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void genreNietGevonden(){}

}

