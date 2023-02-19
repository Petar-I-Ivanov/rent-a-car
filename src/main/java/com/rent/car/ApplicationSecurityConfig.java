package com.rent.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@SuppressWarnings("deprecation")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired private UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(bCryptPasswordEncoder());
		return provider;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf()
				.requireCsrfProtectionMatcher(new AntPathRequestMatcher("/login", HttpMethod.POST.name()))
				.requireCsrfProtectionMatcher(new AntPathRequestMatcher("/actors/addNew", HttpMethod.POST.name()))
				.and()
			.headers()
				.xssProtection()
					.and()
				.contentSecurityPolicy("form-action 'self'").and()
				//.contentSecurityPolicy(getContentSecurityPolicy()).and()
				.frameOptions().sameOrigin()
					.and()
			.authorizeRequests()
				.antMatchers("/login", "/assets/**", "/register", "/actors/addNew").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login").permitAll()
				.defaultSuccessUrl("/index")
				.and()
			.exceptionHandling()
				.accessDeniedPage("/accessDenied")
				.and()
			.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login").permitAll();
	}
	
//	private String getContentSecurityPolicy() {
//		
//		String defaultSrc = "default-src 'self'";
//		String styleSrc = "style-src 'self' fonts.googleapis.com";
//		String fontSrc = "font-src 'self' fonts.gstatic.com 'nonce-" + getNonce() + "'";
//		String scriptSrc = "script-src 'self' "
//						 + "https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js "
//						 + "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js "
//						 + "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js "
//						 + "https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.0/jquery.min.js "
//						 + "https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js";
//		String formAction = "form-action 'self'";
//		
//		return  defaultSrc + "; " +
//				styleSrc + "; " +
//				fontSrc + "; " +
//				scriptSrc + "; " +
//				formAction;
//	}
//	
//	private String getNonce() {
//		
//		final int NONCE_LENGTH = 16;
//		
//		SecureRandom random = new SecureRandom();
//		byte[] nonceBytes = new byte[NONCE_LENGTH];
//		random.nextBytes(nonceBytes);
//		
//		return Base64.getEncoder().encodeToString(nonceBytes);
//	}
}
