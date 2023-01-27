package com.fdmgroup.MicroservicesAssessmentClient.model.employee;

import java.util.List;

import com.fdmgroup.MicroservicesAssessmentClient.model.Employee;

public interface EmployeeRestClient {

	List<Employee> getAllEmployees();
	Employee getEmployeeById(long id);
	Employee createEmployee(Employee emp);
	Employee updateEmployee(Employee emp, long id);
	void deleteEmployee(long id);
	
}
