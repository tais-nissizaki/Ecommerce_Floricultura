package br.com.ecommerce;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests().antMatchers("/**").permitAll()
		.and()
		.cors().and().csrf().disable();
		
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods("GET", "POST", "PUT", "OPTIONS", "PATCH");
	}
	
}
