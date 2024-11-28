package com.shopping.employee.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.employee.entites.Employee;
import com.shopping.employee.entites.RoleMapping;

public interface RoleMappingRepo extends JpaRepository<RoleMapping, Long> {

	RoleMapping findByEmployee(Employee manager);

}
