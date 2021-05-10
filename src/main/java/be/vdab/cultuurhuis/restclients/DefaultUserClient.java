package be.vdab.cultuurhuis.restclients;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.dto.User;
import be.vdab.cultuurhuis.exceptions.VoorstellingNietGevondenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Optional;

@Component
@SessionScope
public class DefaultUserClient implements UserClient {

private final WebClient client;
private final String userURI;
private final Klant klant=null;
private final String VoorstellingURI;
public DefaultUserClient(WebClient.Builder builder, @Value("${cultuurhuis.voorstelling}")String voorstellingurl,@Value("${reqres.user}") String userURI) {
	this.client = builder.build();
	this.userURI = userURI;
	this.VoorstellingURI=voorstellingurl;
}

@Override
public Optional<Klant> findById(long id) {
	try {
		return Optional.of(client.get().uri(userURI, uriBuilder -> uriBuilder.build(id))
				                   .retrieve().bodyToMono(Klant.class).block());
	} catch (WebClientResponseException.NotFound ex) {
		return Optional.empty();
	}
}

@Override
public Optional<Voorstelling> getdataVoostellingen(long id) {
	try {
	 return Optional.of(client.get().uri(VoorstellingURI, uriBuilder -> uriBuilder.build(id))
				.retrieve().bodyToMono(Voorstelling.class).block());
	}catch (WebClientResponseException.NotFound ex){
		return Optional.empty();
	}
	
}


}
