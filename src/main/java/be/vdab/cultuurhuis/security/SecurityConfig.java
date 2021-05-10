package be.vdab.cultuurhuis.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.SpringSecurityCoreVersion;

import javax.sql.DataSource;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String GAST= "gast";
	private static final  String KLANT="klant";
private final DataSource dataSource;

SecurityConfig(DataSource dataSource) {
	this.dataSource = dataSource;
}

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.jdbcAuthentication().dataSource(dataSource)
	.usersByUsernameQuery("select gebruikersnaam as username, paswoord as password, actief as true" +
			                      "from klanten where gebruikersnaam=?")
	.authoritiesByUsernameQuery("select gebruikersnaam as username, klant as authorities" +
			                            "from klanten");
}

@Override public void configure(WebSecurity web) {
	web.ignoring()
			.mvcMatchers("/images/**")
 .mvcMatchers("/css/**")
		  .mvcMatchers("/js/**");
	
}

@Override
protected void configure(HttpSecurity http) throws Exception {
	http.formLogin();
	http.authorizeRequests(requests -> requests
	
	
	.mvcMatchers("/**").permitAll()
	.mvcMatchers("/**").hasAnyAuthority(KLANT,GAST)
			                                   .mvcMatchers("/users").hasAuthority(KLANT)
			);
			
	
	
	http.logout(logout->logout.logoutSuccessUrl("/"));
	
	
}
	
	

}
