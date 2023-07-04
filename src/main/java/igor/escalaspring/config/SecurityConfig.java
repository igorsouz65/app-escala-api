package igor.escalaspring.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import igor.escalaspring.service.CustomUserDetailsService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
				.and().csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.GET, SecurityConstants.SIGN_UP_URL).permitAll()
				.antMatchers("/*/protected/**").hasRole("USER")
				.antMatchers("/*/admin/**").hasRole("ADMIN")
				.and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailsService));
	}


	public void configure(WebSecurity web) throws Exception {

		//libera o swagger
		web.ignoring().antMatchers("/swagger-ui/**", "/v3/api-docs/**");
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/*/protected/**").hasRole("USER").antMatchers("/*/admin/**")
//				.hasRole("ADMIN").and().httpBasic().and().csrf().disable();
//	}

	/* Auth in memory */

	/*
	 * @Bean public UserDetailsService userDetailsService() {
	 * 
	 * User.UserBuilder users = User.withDefaultPasswordEncoder();
	 * InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	 * manager.createUser(users.username("user").password("password").roles("USER").
	 * build());
	 * manager.createUser(users.username("admin").password("password").roles("USER",
	 * "ADMIN").build()); return manager;
	 * 
	 * }
	 */

}
