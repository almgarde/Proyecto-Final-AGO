package es.iessoterohernandez.ProyectoFinalAGO.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		
		return bCryptPasswordEncoder;
	}

	String[] resources = new String[] { "/i18n/**", "/css/**", "/datepicker/**", "/images/**", "/js/**", "/views/**" };

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.authorizeRequests()
		.antMatchers(resources).permitAll()
		.antMatchers("/","/home", "/facilities", "/links", "/members", "/news/**", "/projects", "/publications", "/thesis").permitAll()
			.anyRequest().authenticated()
			.and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
	        .defaultSuccessUrl("/management")
	        .failureUrl("/home?error=true")
	        .usernameParameter("username")
	        .passwordParameter("password")
	        .and()
        .logout()
	        .permitAll()
	        .logoutSuccessUrl("/home?logout")
	     .and()
	     .csrf().disable();
		
	}
	
	
	
	

}
