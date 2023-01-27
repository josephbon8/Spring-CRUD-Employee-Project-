package com.fdmgroup.MicroservicesAssessment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.MicroservicesAssessment.model.Employee;
import com.fdmgroup.MicroservicesAssessment.repository.EmployeeRepository;



@Service
public class EmployeeService {

	private EmployeeRepository empRepo;
	
	
     @Autowired
	public EmployeeService(EmployeeRepository empRepo) {
		super();
		this.empRepo = empRepo;
	}
	public List<Employee> getAllEmployees(){
		return empRepo.findAll();
	}
	public Employee createEmployee(Employee emp) {
		return empRepo.save(emp);
	}
	
	public Employee getEmployeeById(long id) throws EmployeeException {
		Optional<Employee> employeeOpt= empRepo.findById(id);
		if(!employeeOpt.isPresent()) {
			throw new EmployeeException("The employee with id" +id+ " is not found");
		}
		return employeeOpt.get();
	}
	
	public Employee updateEmployee(Employee emp, long id) {
		emp.setId(id);
		return empRepo.save(emp);
	} 
	public void deleteEmployeeById(long id) {
		empRepo.deleteById(id);
	}
	
}
