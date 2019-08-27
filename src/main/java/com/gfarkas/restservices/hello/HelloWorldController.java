package com.gfarkas.restservices.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	
	private ResourceBundleMessageSource messageSource;

	@Autowired
	public void setMessageSource(ResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}
	//Simple method
	//URI - /helloworld
	//Get
	//@RequestMapping(method = RequestMethod.GET, path = "/helloworld" )
	@GetMapping("/helloworld1")
	public String helloWorld() {
		
		return "Hello World";
		
	}
	@GetMapping("/helloworldbean")
	public UserDetails helloWorldBean() {
		
		return new UserDetails("Gabor", "Farkas", "Budapest");
		
	}
	
	@GetMapping("/hello-i18n")
	public String getMessagesInI18NFormat (@RequestHeader(name="Accept-Language", required=false) String locale) {
		
		return messageSource.getMessage("label.hello", null, new Locale(locale));
		
	}
	
	@GetMapping("/hello-i18n_2")
	public String getMessagesInI18NFormat2 () {
		
		return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
		
	}
	
}
