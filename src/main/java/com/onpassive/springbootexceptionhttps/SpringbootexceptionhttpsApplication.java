package com.onpassive.springbootexceptionhttps;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.onpassive.springbootexceptionhttps.emp.Employee;
import com.onpassive.springbootexceptionhttps.emp.EmployeeRepository;

@SpringBootApplication
public class SpringbootexceptionhttpsApplication {

	/*
	 * @Autowired EmployeeRepository employeeRepository;
	 * 
	 * @PostConstruct public void initUsers() { List<Employee> users = Stream
	 * .of(new Employee(101, "javatechie", "password", "javatechie@gmail.com",
	 * "9701316387"), new Employee(102, "user1", "pwd1", "user1@gmail.com",
	 * "9701316387"), new Employee(103, "user2", "pwd2", "user2@gmail.com",
	 * "9701316387"), new Employee(104, "user3", "pwd3", "user3@gmail.com",
	 * "9701316387")) .collect(Collectors.toList());
	 * employeeRepository.saveAll(users); }
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringbootexceptionhttpsApplication.class, args);
	}

}
