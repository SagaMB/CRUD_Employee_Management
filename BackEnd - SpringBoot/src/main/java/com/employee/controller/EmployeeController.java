package com.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.employee.entities.Employee;
import com.employee.exceptions.ResourceNotFoundException;
import com.employee.repository.EmployeeRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	// get All Employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	// create Employee
	@PostMapping("/employee")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	// get Employee by ID
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
		Employee employee = this.employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not existed with id:"+ id));
		return ResponseEntity.ok(employee);
	}
	
	// Updating the Employee details
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee updatedEmployee) {
		Employee employee = this.employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id: "+ id));
		employee.setFirstName(updatedEmployee.getFirstName());
		employee.setLastName(updatedEmployee.getLastName());
		employee.setEmailId(updatedEmployee.getEmailId());
		
		Employee save = this.employeeRepository.save(employee);
		return ResponseEntity.ok(save);
	}
	
	// Delete Employee By Id
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable long id){
		Employee employee = this.employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee does not exist by id: "+ id));
		this.employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("Deleted", true);
		return ResponseEntity.ok(response);
	}
}
