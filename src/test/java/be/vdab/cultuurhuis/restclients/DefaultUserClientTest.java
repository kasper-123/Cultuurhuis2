package be.vdab.cultuurhuis.restclients;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
@SpringBootTest
public class DefaultUserClientTest extends AbstractTransactionalJUnit4SpringContextTests{
	private final DefaultUserClient client;

public DefaultUserClientTest(DefaultUserClient client) {
	this.client = client;
}

@Test
void bestaandeUser(){
	assertThat(client.findById(1)).hasValueSatisfying(user ->
			                                                 assertThat(user.getData().getId()).isOne());

}
@Test void findOnbestaandeUser() {
	assertThat(client.findById(-1)).isEmpty();
}
}
