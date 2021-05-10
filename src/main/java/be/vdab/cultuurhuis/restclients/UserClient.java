package be.vdab.cultuurhuis.restclients;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.dto.User;

import java.util.Optional;

public interface UserClient {
	
	Optional<User>findById(long id);
	
	
	
}
