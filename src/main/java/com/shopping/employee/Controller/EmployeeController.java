package com.shopping.employee.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.employee.entites.Employee;
import com.shopping.employee.service.EmployeeService;
import com.shopping.employee.serviceImpl.EmployeeServiceImpl;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	Map<String,Object> map=new HashMap<>();
	@PostMapping("/add-roles")
	public ResponseEntity<Map<String, Object>> addRoles(){
		Map<String,Object> map= this.employeeService.addRoles();
		return ResponseEntity.ok().body(map);
	}
	
	
	@PostMapping("/add-employee")
	public ResponseEntity<Map<String, Object>> addEmployee(@RequestBody Employee employee) throws RoleNotFoundException
	{
		Map<String,Object> map=this.employeeService.createEmployee(employee);
		return ResponseEntity.ok().body(map);
				}
	
	@PostMapping("/assign-manager-for-employee/{managerId}/{empId}")
	public ResponseEntity<Map<String,Object>> assignManager(@PathVariable long managerId,@PathVariable long empId)
	{
		Map<String, Object> map=this.employeeService.assignManager(managerId,empId);
		return ResponseEntity.ok().body(map);
	}
	
	
//	@GetMapping("get-list-of-reporting-employees-for-manager/{empId}")
//	public ResponseEntity<Map<String, Object>> getAllReportingEmployeesForManager(@PathVariable long empId)
//	{
//		Map<String,Object> map=this.employeeService.getAllReportingEmployeesForManager(empId);
//		return ResponseEntity.ok().body(map);
//	}

}
