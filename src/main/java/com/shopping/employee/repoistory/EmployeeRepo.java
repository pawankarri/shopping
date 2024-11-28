package com.shopping.employee.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.employee.entites.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
