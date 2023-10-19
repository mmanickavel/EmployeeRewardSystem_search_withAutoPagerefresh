package com.employees.springboot.repository;

import static org.springframework.data.jpa.domain.Specification.where;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.employees.springboot.model.Employee;
import com.employees.springboot.model.SearchFilter;

@Component
public class CustomEmployeeRepository {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getQueryResult(List<SearchFilter> filters){
        if(filters.size()>0) {
            return employeeRepository.findAll(getSpecificationFromFilters(filters));
        }else {
            return employeeRepository.findAll();
        }
    }
	
	private Specification<Employee> getSpecificationFromFilters(List<SearchFilter> filter) {
        Specification<Employee> specification = where(createSpecification(filter.remove(0)));
        if(filter != null) {
        for (SearchFilter input : filter) {
            specification = specification.and(createSpecification(input));
        }
        }
        return specification;
    }
	
	private Specification<Employee> createSpecification(SearchFilter input) {
        switch (input.getOperator()){
            case EQUALS:
                return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(input.getField()), Integer.valueOf(input.getValue()));
            case GREATER_THAN:
                return (root, query, criteriaBuilder) ->
                criteriaBuilder.gt(root.get(input.getField()), Integer.valueOf(input.getValue()));
            case LESS_THAN:
                return (root, query, criteriaBuilder) ->
                criteriaBuilder.lt(root.get(input.getField()), Integer.valueOf(input.getValue()));
            case LIKE:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(root.get(input.getField()), "%"+input.getValue()+"%");
            case STARTS_WITH:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(root.get(input.getField()), input.getValue()+"%");
            case ENDS_WITH:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(root.get(input.getField()), "%"+input.getValue());
            default:
                throw new RuntimeException("Operation not supported yet");
        }
    }	
	
}
