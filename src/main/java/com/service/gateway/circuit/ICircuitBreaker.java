package com.service.gateway.circuit;

/*
 * Implement Circuit Breaker for Services on Gateway
 */
public interface ICircuitBreaker {
	
	public String onFailure(String args);

}
