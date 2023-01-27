package com.fdmgroup.MicroservicesAssessment.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.MicroservicesAssessment.model.Employee;
import com.fdmgroup.MicroservicesAssessment.service.EmployeeException;
import com.fdmgroup.MicroservicesAssessment.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins= "http://localhost:4200")
@RequestMapping("/api/employees")
public class EmployeeController {

	private EmployeeService empService;
    
	@Autowired
	public EmployeeController(EmployeeService empService) {
		super();
		this.empService = empService;
	}
	
	//------Swagger---//
	@Operation(summary = "Getting all employees")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                                                                        }
                                        , headers = {@Header(name = "location", description = "URI To Location")
                                                    }
                                        )

                    }
                )
	
	@GetMapping
	public ResponseEntity<List<Employee>> getEmployees(){
		return ResponseEntity.ok(empService.getAllEmployees());
	}
	
	//------Swagger---//
		@Operation(summary = "Create an Employee")
	    @ApiResponses(value = {@ApiResponse(responseCode = "201", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
	                                                                        }
	                                        , headers = {@Header(name = "location", description = "URI To Location")
	                                                    }
	                                        )
	                      ,@ApiResponse(responseCode= "404", description= "Employee is not Found")
	                    }
	                )
	
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee emp) {
		Employee createdEmp= empService.createEmployee(emp);
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(createdEmp.getId()).toUri();
		return ResponseEntity.created(location).body(createdEmp);
	}
	
	//------Swagger---//
	@Operation(summary = "Getting an employee")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                                                                        }
                                        , headers = {@Header(name = "location", description = "URI To Location")
                                                    }
                                        )
                      ,@ApiResponse(responseCode= "404", description= "Employee is not Found")
                    }
                )
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") long empId) throws EmployeeException {
		Employee foundEmp= empService.getEmployeeById(empId);
		return ResponseEntity.ok(foundEmp);
		
	}
	
	//-----Swagger-----//
	
		@Operation(summary = "Update Employee")
	    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
	                                                                        }
	                                        , headers = {@Header(name = "location", description = "URI To Location")
	                                                    }
	                                        )
	                      ,@ApiResponse(responseCode= "404", description= "Employee is not Found")
	                    }
	                )
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updatedEmployee(@RequestBody Employee employee, @PathVariable("id") long empid) {
		Employee updatedEmp= empService.updateEmployee(employee, empid);
		return ResponseEntity.ok(updatedEmp);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		empService.deleteEmployeeById(id);
	}
}
