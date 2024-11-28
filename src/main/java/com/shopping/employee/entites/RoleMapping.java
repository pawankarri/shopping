package com.shopping.employee.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleMapping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long roleMappingId;
	
	@OneToOne
	@JoinColumn(name = "roleId")
	private Role roleId;
	
	@OneToOne
	@JoinColumn(name = "empId")
	private Employee employee;

}
