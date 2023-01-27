package com.fdmgroup.MicroservicesAssessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.MicroservicesAssessment.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
