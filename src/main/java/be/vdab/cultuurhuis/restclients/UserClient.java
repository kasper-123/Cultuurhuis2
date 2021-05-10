package be.vdab.cultuurhuis.restclients;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.dto.User;

import java.util.Optional;

public interface UserClient {
	
public	Optional<Klant>findById(long id);

public Optional<Voorstelling>getdataVoostellingen(long id);
	
}
