package be.vdab.cultuurhuis.restcontrollers;


import be.vdab.cultuurhuis.domain.Voorstelling;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("voorstellingen")
@CrossOrigin(exposedHeaders = "Location")
@ExposesResourceFor(Voorstelling.class)
public class ReservatieController {
}
