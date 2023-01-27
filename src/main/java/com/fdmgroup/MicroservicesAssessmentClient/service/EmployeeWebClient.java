package com.fdmgroup.MicroservicesAssessmentClient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fdmgroup.MicroservicesAssessmentClient.model.Employee;
import com.fdmgroup.MicroservicesAssessmentClient.model.employee.EmployeeRestClient;

import reactor.core.publisher.Mono;

@Component
public class EmployeeWebClient implements EmployeeRestClient {
	
	private WebClient empClient;
	
	
    @Autowired
	public EmployeeWebClient(WebClient empClient) {
		super();
		this.empClient = empClient;
	}

	@Override
	public List<Employee> getAllEmployees() {
	    return empClient.get()
	    		.retrieve()
		        .bodyToFlux(Employee.class)
		        .collectList()
		        .block();
	}

	@Override
	public Employee getEmployeeById(long id) {
		
		return empClient.get()
				.uri("/" + id)
				.retrieve()
				.onStatus(status-> status.value()==HttpStatus.NOT_FOUND.value(),
				response-> Mono.error(new EmployeeException("Employee " + id + " not found")))
				.bodyToMono(Employee.class)
				.block();
	}

	@Override
	public Employee createEmployee(Employee emp) {
		return empClient.post()
				.bodyValue(emp)
				.retrieve()
				.bodyToMono(Employee.class)
				.block();
	}

	@Override
	public Employee updateEmployee(Employee emp, long id) {
		return empClient.put()
				.uri("/" + id)
				.bodyValue(emp)
				.retrieve()
				.bodyToMono(Employee.class)
				.block();
	}

	@Override
	public void deleteEmployee(long id) {
		empClient.delete()
		.uri("/"+ id)
		.retrieve()
		.toBodilessEntity()
		.block();
		
	}

}
