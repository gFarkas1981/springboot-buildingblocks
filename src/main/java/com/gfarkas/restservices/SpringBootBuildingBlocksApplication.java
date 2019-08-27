package com.gfarkas.restservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

//
@SpringBootApplication
public class SpringBootBuildingBlocksApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBuildingBlocksApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver () {
		
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.UK);
		
		return localeResolver;
		
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource () {
		
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.addBasenames("messages");
		return messageSource;
		
	}
	
	
}
