package com.revature.service;

import java.util.List;
import java.util.Optional;

import com.revature.dao.EmployeeDAO;
import com.revature.models.Employee;

public class EmployeeService {
	private EmployeeDAO edao;
	
	public EmployeeService(EmployeeDAO edao) {
		super();
		this.edao = edao;
	}
	public List<Employee> findAll(){
		return edao.findAll();
	}
	
	public Employee confirmLogin(String username, String password) {
		Optional<Employee> possibleEmp = edao.findAll()
		.stream()
		.filter(e -> (e.getUsername().equals(username) && e.getPassword().equals(password)))
		.findFirst();
		
		
		
		return (possibleEmp.isPresent()? possibleEmp.get() : null);
	}
	
	public int insert(Employee e) {
		return edao.insert(e);
		
	}
	
	public boolean update(Employee e) {
		return edao.update(e);
	}
	
	public boolean delete(Employee e) {
		return edao.delete(e);
	}
}
