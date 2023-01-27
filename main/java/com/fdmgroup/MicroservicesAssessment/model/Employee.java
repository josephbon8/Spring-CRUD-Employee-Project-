package com.fdmgroup.MicroservicesAssessment.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Employee {
    
	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
    private String firstname;
	
	@NotNull
    private String lastname;
	@NotNull
    private long salary;
	@NotNull
    private String state;
	@NotNull
    private String country;
    
	public Employee( String firstname, String lastname, long salary, String state, String country) {
		super();
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.salary = salary;
		this.state = state;
		this.country = country;
	}
    
	public Employee() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		return Objects.hash(country, firstname, id, lastname, salary, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(country, other.country) && Objects.equals(firstname, other.firstname) && id == other.id
				&& Objects.equals(lastname, other.lastname) && salary == other.salary
				&& Objects.equals(state, other.state);
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", salary=" + salary
				+ ", state=" + state + ", country=" + country + "]";
	}
    
    
}
