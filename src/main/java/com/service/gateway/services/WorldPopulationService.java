package com.service.gateway.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.service.gateway.circuit.ICircuitBreaker;

@Service
public class WorldPopulationService implements ICircuitBreaker {
	
	static Logger logger = Logger.getLogger(WorldPopulationService.class);
	@Autowired
    RestTemplate restTemplate;
	
	public WorldPopulationService(RestTemplate rest) {
	    this.restTemplate = rest;
	  }
	
	@HystrixCommand(fallbackMethod = "onFailure")
	public String populationStatusByCountry(String country) {
		logger.info("Request transfered to delegator service for "+country+" at "+System.currentTimeMillis());
		String response = restTemplate.exchange("http://worldpopulation/getPopulation?country={country}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, country).getBody();
		logger.info("Response received at delegator service for "+country+" at "+System.currentTimeMillis());
	  return response;
	  }


	@Override
	public String onFailure(String country) {
		// TODO Auto-generated method stub
		return "Please try after sometime";
	}
	
	@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
