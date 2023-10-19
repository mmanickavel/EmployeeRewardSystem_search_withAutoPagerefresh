package com.employees.springboot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employees.springboot.model.Employee;
import com.employees.springboot.model.SearchFilter;
import com.employees.springboot.service.EmployeeService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/coins")
	private List<Employee> getAllEmployee() {
		return employeeService.getAllEmployeeService();
	}
	
	@PostMapping("/coin")
	private Employee createEmployee(@RequestBody Employee employee) {	
		return employeeService.createEmployeeService(employee);
	}
	
	@PostMapping("/search")
	private List<Employee> searchEmployee(@RequestBody List<SearchFilter> filters) {	
		return employeeService.searchEmployeeService(filters);
	}
	
	

}
