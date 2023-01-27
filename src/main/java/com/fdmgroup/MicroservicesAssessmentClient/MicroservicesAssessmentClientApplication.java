package com.fdmgroup.MicroservicesAssessmentClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MicroservicesAssessmentClientApplication {

	private final String CONTACT_BASE_URL = "http://localhost:8093/api/employees";
	public static void main(String[] args) {
		SpringApplication.run(MicroservicesAssessmentClientApplication.class, args);
	}

	
	@Bean
	public WebClient.Builder getBuilder(){
		return WebClient.builder();
	}
	
	@Bean
	public WebClient getContactWebClient(WebClient.Builder builder) {
		return  builder.baseUrl(CONTACT_BASE_URL)
						.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.build();
	}
	
}
