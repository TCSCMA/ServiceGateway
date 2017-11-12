package com.service.gateway.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.service.gateway.services.WorldPopulationService;


@RestController
public class ServiceGatewayController {
	
	static Logger logger = Logger.getLogger(ServiceGatewayController.class);
	@Autowired
	private WorldPopulationService worldPopulationService;
	
	@Bean
	public RestTemplate rest(RestTemplateBuilder builder) {
	  return builder.build();
	}
	
	@RequestMapping(value = "/worldpopulation/getPopulation", method = RequestMethod.GET)
	public String populationStatusByCountry (@RequestParam String country) {
		logger.info("Request reached at Gateway for "+country+" at "+System.currentTimeMillis());
		String response = worldPopulationService.populationStatusByCountry(country);
		logger.info("Response received at Gateway for "+country+" at "+System.currentTimeMillis());
		return response;
		
	}
	
	
	@RequestMapping(value = "/worldcountry/getCountry", method = RequestMethod.GET)
	public String getCountryDetails (@RequestParam String country) {
		logger.info("Request reached at Gateway for "+country+" at "+System.currentTimeMillis());
		String response = worldPopulationService.populationStatusByCountry(country);
		logger.info("Response received at Gateway for "+country+" at "+System.currentTimeMillis());
		return response;
		
	}
	
	/*@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
*/
}
