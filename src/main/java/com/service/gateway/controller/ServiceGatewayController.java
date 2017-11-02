package com.service.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServiceGatewayController {
	
	@Autowired
    RestTemplate restTemplate;
	
	@RequestMapping(value = "/worldpopulation/getPopulation", method = RequestMethod.GET)
	public String populationStatusByCountry (@RequestParam String country) {
		
		System.out.println("Getting Country details for " + country);
		String response = restTemplate.exchange("http://worldpopulation/getPopulation?country={country}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, country).getBody();
		
		return response;
		
	}
	
	@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
