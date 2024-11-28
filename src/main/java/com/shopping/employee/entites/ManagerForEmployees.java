package com.shopping.employee.entites;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ManagerForEmployees {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long managerEmployeeId;
	
	@ManyToOne
	@JoinColumn(name = "roleMappingId")
	private RoleMapping roleMapping;
	
	@OneToMany
	@JoinColumn(name = "managerId")
	private List<Employee> employee;

}
