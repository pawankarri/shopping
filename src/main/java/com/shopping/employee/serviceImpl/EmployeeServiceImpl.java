package com.shopping.employee.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopping.employee.entites.Employee;
import com.shopping.employee.entites.ManagerForEmployees;
import com.shopping.employee.entites.Role;
import com.shopping.employee.entites.RoleMapping;
import com.shopping.employee.repoistory.EmployeeRepo;
import com.shopping.employee.repoistory.ManagerForEmployeesRepo;
import com.shopping.employee.repoistory.RoleMappingRepo;
import com.shopping.employee.repoistory.RoleRepo;
import com.shopping.employee.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private RoleRepo roleRepository;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private RoleMappingRepo roleMappingRepo;
	
	@Autowired
	private ManagerForEmployeesRepo managerForEmployeesRepo;
	
    Map<String, Object> map=new HashMap<>();
	@Override
	public Map<String, Object> addRoles() {
		List<Role> roleList=null;
		 if (roleRepository.count() == 0) {
	           roleList=  roleRepository.saveAll(List.of(
	                new Role(1000L, "employee"),
	                new Role(1001L, "employee,floor supervisor"),
	                new Role(1002L, "employee,floor supervisor,sale supervisor"),
	                new Role(1003L, "employee,floor supervisor,sale supervisor,Accountant"),
	                new Role(1004L, "employee,floor supervisor,sale supervisor,Accountant,admin")
	            ));
	        }
		map.put("status", HttpStatus.CREATED.value());
		map.put("message", "inserted Successfully");
		map.put("result", roleList);
		return map;
	}
	@Override
	public Map<String, Object> createEmployee(Employee employee) throws RoleNotFoundException {
		
		this.employeeRepo.save(employee);
		RoleMapping roleMapping=new RoleMapping();
		if(employee.getEmpId()==100)
		{
			Role role=this.roleRepository.findById((long) 1004).orElseThrow(()->new RoleNotFoundException("this is role is not there"));
			
			roleMapping.setRoleId(role);
			roleMapping.setEmployee(employee);
		}
		else
		{
			Role role=this.roleRepository.findById((long) 1000).orElseThrow(()->new RoleNotFoundException("this is role is not there"));
			roleMapping.setRoleId(role);
			roleMapping.setEmployee(employee);
		}
		this.roleMappingRepo.save(roleMapping);
		
		
		
		map.put("status", HttpStatus.CREATED.value());
		map.put("message", "created Successfully");
		map.put("result", employee);
		return map;
	}
	
	
	
	@Override
	public Map<String, Object> assignManager(long managerId, long empId) {
	Employee employee=this.employeeRepo.findById(empId).orElseThrow(()->new RuntimeException("employeeNotFound"));
	Employee manager=this.employeeRepo.findById(managerId).orElseThrow(()->new RuntimeException("employeeNotFound"));
	RoleMapping roleMapping=	this.roleMappingRepo.findByEmployee(manager);
	if(roleMapping==null)
	{
		throw new RuntimeException("manager not found with this id");
	}
    long roleId=roleMapping.getRoleId().getRoleId();
	if(roleId== 1001 || roleId==1002 || roleId==1003 || roleId==1004)
	{
          ManagerForEmployees managerForEmployees=new ManagerForEmployees();
          managerForEmployees.setRoleMapping(roleMapping);
          managerForEmployees.setEmployee(List.of(employee));
          this.managerForEmployeesRepo.save(managerForEmployees);
	}
		map.put("status", HttpStatus.CREATED.value());
		map.put("message", "manager set for employee successfully");
		return map;
	}
//	@Override
//	public Map<String, Object> getAllReportingEmployeesForManager(long empId) {
//		Employee employee=this.employeeRepo.findById(empId).orElseThrow(()->new RuntimeException("employeeNotFound"));
//		
//		RoleMapping roleMapping=	this.roleMappingRepo.findByEmployee(employee);
//		if(roleMapping==null)
//		{
//			throw new RuntimeException("manager not found with this id");
//		}
//		List<ManagerForEmployees> managerForEmployees=this.managerForEmployeesRepo.findAllById(roleMapping);
//		if(managerForEmployees!=null)
//		{
//			map.put("status", HttpStatus.OK.value());
//			map.put("message", "Fetched successfully");
//			map.put("result", managerForEmployees);
//		}
//		else
//		{
//			map.put("status", HttpStatus.OK.value());
//			map.put("message", "Fetched successfully");
//			map.put("result", "No employees under this reporting manager");
//		}
//		return map;
//	}

	
//	public Map<String, Object> getAllReportingEmployeesForManager(long empId) {
//	   
//	    Map<String, Object> map = new HashMap<>();
//
//	   
//	    Employee employee = this.employeeRepo.findById(empId)
//	            .orElseThrow(() -> new RuntimeException("Employee not found"));
//
//	   
//	    RoleMapping roleMapping = this.roleMappingRepo.findByEmployee(empId);
//	    if (roleMapping == null) {
//	        throw new RuntimeException("Manager not found with this ID");
//	    }
//
//	   
//	    List<ManagerForEmployees> managerForEmployees = this.managerForEmployeesRepo.findAllByRoleMapping(roleMapping);
//
//	   
//	    List<Map<String, Object>> reportingEmployeesHierarchy = new ArrayList<>();
//	    for (ManagerForEmployees managerEmployee : managerForEmployees) {
//	        long reportingEmpId = managerEmployee.getEmpId();
//	       
//	        Map<String, Object> subordinateHierarchy = getAllReportingEmployeesForManager(reportingEmpId);
//	        reportingEmployeesHierarchy.add(subordinateHierarchy);
//	    }
//
//	    Map<String, Object> managerDetails = new HashMap<>();
//	    managerDetails.put("employeeId", employee.getEmpId());
//	    managerDetails.put("name", employee.getName());
//	    managerDetails.put("phoneNumber", employee.getPhoneNumber());
//	    managerDetails.put("subordinates", reportingEmployeesHierarchy);
//
//	    map.put("status", HttpStatus.OK.value());
//	    map.put("message", "Fetched successfully");
//	    map.put("result", managerDetails);
//
//	    return map;
//	}

	
}
