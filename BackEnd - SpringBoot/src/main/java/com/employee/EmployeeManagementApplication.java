package com.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EntityScan(basePackages = {"com.employee.entities;"})
//@EnableJpaRepositories("com.employee.repository")
public class EmployeeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}

}
