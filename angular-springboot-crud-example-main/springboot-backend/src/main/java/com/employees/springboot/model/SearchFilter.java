package com.employees.springboot.model;

public class SearchFilter {
	
	private String field;
	
	private String value;

	private QueryOperator operator;
	
	public SearchFilter(String field, String value, QueryOperator operator) {
		super();
		this.field = field;
		this.value = value;
		this.operator = operator;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public QueryOperator getOperator() {
		return operator;
	}

	public void setOperator(QueryOperator operator) {
		this.operator = operator;
	}
	
}
