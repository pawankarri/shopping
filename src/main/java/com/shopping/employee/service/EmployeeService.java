package com.shopping.employee.service;

import java.util.Map;

import javax.management.relation.RoleNotFoundException;

import org.springframework.http.ResponseEntity;

import com.shopping.employee.entites.Employee;


public interface EmployeeService {

	Map<String, Object> addRoles();

	Map<String, Object> createEmployee(Employee employee) throws RoleNotFoundException;

	Map<String, Object> assignManager(long managerId, long empId);

	//Map<String, Object> getAllReportingEmployeesForManager(long empId);

}
