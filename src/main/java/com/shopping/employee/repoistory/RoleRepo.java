package com.shopping.employee.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.employee.entites.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

	
}
