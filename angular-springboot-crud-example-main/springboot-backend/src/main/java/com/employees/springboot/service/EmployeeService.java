package com.employees.springboot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employees.springboot.model.Employee;
import com.employees.springboot.model.SearchFilter;
import com.employees.springboot.repository.CustomEmployeeRepository;
import com.employees.springboot.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private CustomEmployeeRepository customEmployeeRepository;
	
	public List<Employee> getAllEmployeeService() {
		List<Employee> employees = employeeRepository.findAll();
		Map<String, Integer> sum = new HashMap<String, Integer>();
		List<Employee> employees1 = new ArrayList<Employee>();
		int id = 1;
		if(employees!=null) {
		 sum = employees.stream().collect(
                Collectors.groupingBy(Employee::getName, Collectors.summingInt(Employee::getCoins)));
		 for (Entry<String , Integer> entry: sum.entrySet() ) {			 
				Employee employee = new Employee();				
				employee.setId(id);
				employee.setName(entry.getKey());
				employee.setCoins(entry.getValue());
				employees1.add(employee);
				id++;		
			}
		}
		/*Map<String, Integer> uniqueKey = new HashMap();
		for (ListIterator<Employee> iter = employees.listIterator(); iter.hasNext(); ) {
		    Employee employee = iter.next();
		    if(!uniqueKey.containsKey(employee.getName())) {
		    	uniqueKey.put(employee.getName(), employee.getCoins());
		    } else {
		    	int currentValue = uniqueKey.get(employee.getName());
		    	uniqueKey.put(employee.getName(), employee.getCoins() + currentValue);
		    }
		    }*/		
		
		return employees1;
	}
	
	public Employee createEmployeeService(Employee employee) {		
		/*Optional<Employee> matchingObject = employeeRepository.findAll().stream().
			    filter(e -> e.getName().equals(employee.getName())).
			    findFirst();
		if(matchingObject.isPresent()) {
			Employee existingEmployee = matchingObject.get();
			existingEmployee.setCoins(existingEmployee.getCoins() + employee.getCoins() );
			return employeeRepository.save(existingEmployee);
		}*/
		
		return employeeRepository.save(employee);
	}
	
	public List<Employee> searchEmployeeService(List<SearchFilter> filters) {			
		
		return customEmployeeRepository.getQueryResult(filters);
	}

}
