package be.vdab.cultuurhuis.restclients;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.dto.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Optional;

@Component
public class DefaultUserClient implements UserClient {

private final WebClient client;
private final String userURI;

public DefaultUserClient(WebClient.Builder builder, @Value("${reqres.user}") String userURI) {
	this.client = builder.build();
	this.userURI = userURI;
}

@Override
public Optional<User> findById(long id) {
	try {
		return Optional.of(client.get().uri(userURI, uriBuilder -> uriBuilder.build(id))
				                   .retrieve().bodyToMono(User.class).block());
	} catch (WebClientResponseException.NotFound ex) {
		return Optional.empty();
	}
}
}
