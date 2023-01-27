package com.fdmgroup.MicroservicesAssessmentClient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.MicroservicesAssessmentClient.model.Employee;
import com.fdmgroup.MicroservicesAssessmentClient.model.employee.EmployeeRestClient;

@Service
public class EmployeeServiceClient {

	
	private EmployeeRestClient empRestClient;
     
	@Autowired
	public EmployeeServiceClient(EmployeeRestClient empRestClient) {
		super();
		this.empRestClient = empRestClient;
	}
	
	public List<Employee> getAllEmployees(){
		return empRestClient.getAllEmployees();
	}
	
	public Employee getEmployee(long id) {
		return empRestClient.getEmployeeById(id);
	}
	
	public Employee createEmployee(Employee emp) {
		return empRestClient.createEmployee(emp);
	}
	
	public Employee updateEmployee(Employee emp, long id) {
		return empRestClient.updateEmployee(emp, id);
	}
	
	public void deleteEmployee(long id) {
		empRestClient.deleteEmployee(id);
	}
}
