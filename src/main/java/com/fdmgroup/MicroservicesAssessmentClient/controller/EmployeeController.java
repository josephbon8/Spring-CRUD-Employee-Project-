package com.fdmgroup.MicroservicesAssessmentClient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.MicroservicesAssessmentClient.model.Employee;
import com.fdmgroup.MicroservicesAssessmentClient.service.EmployeeServiceClient;

@Controller
public class EmployeeController {

	private EmployeeServiceClient empServiceClient;

	public EmployeeController(EmployeeServiceClient empServiceClient) {
		super();
		this.empServiceClient = empServiceClient;
	}
	
	@GetMapping("/")
	public String homePage() {
		return "index";
	}
	
	@GetMapping("/createAEmployee")
	public String goToCreateEmployee(Model model) {
		model.addAttribute("emptyEmployee", new Employee());
		return "createEmployee";
	}
	
	@PostMapping("/employeeCreation")
	public String createEmployee(Employee emp, Model model) {
		Employee savedEmployee=empServiceClient.createEmployee(emp);
		model.addAttribute("savedEmployee",savedEmployee);
		return "fullInformation";
		
	}
	
	@GetMapping("/updateEmployee/{id}")
	public String updateEmployee(@PathVariable long id ,Model model) {
		Employee updatedEmp= empServiceClient.getEmployee(id);
		model.addAttribute("updatedEmp", updatedEmp);
		 return "updateEmployee";
	}
	
	@GetMapping("/fullInfo/{id}")
	public String displayFullInfo(@PathVariable long id ,Model model) {
		Employee emp= empServiceClient.getEmployee(id);
		model.addAttribute("savedEmployee", emp);
		return"fullInformation";
	}
	
	@PostMapping("/confirmUpdate/{id}")
	public String processUpdateEmployee(@PathVariable long id, Employee emp) {
		 Employee empUpdate= empServiceClient.updateEmployee(emp,id);
		
		
		return "index";
	}
	
	
	
	@GetMapping("/{id}")
	public String findEmployee(@PathVariable long id, Model model) {
		model.addAttribute("foundEmployee", empServiceClient.getEmployee(id));
		return "employeeInfo";
	}
	@GetMapping("/displayAllEmployees")
	public String showAllEmployees(Model model) {
		model.addAttribute("employees", empServiceClient.getAllEmployees());
		return "showAllEmployees";
	}
	
	@GetMapping("/displayEmployeeInfo")
	public String goToDisplayEmployeeInfo() {
		return"employeeInfo";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable long id) {
		empServiceClient.deleteEmployee(id);
		return "index";
	}
	
	
	
}
