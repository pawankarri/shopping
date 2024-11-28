package com.shopping.employee.repoistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.employee.entites.ManagerForEmployees;
import com.shopping.employee.entites.RoleMapping;

public interface ManagerForEmployeesRepo extends JpaRepository<ManagerForEmployees, Long>{

	//List<ManagerForEmployees> findAllById(RoleMapping roleMapping);

}
