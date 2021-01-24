package com.onpassive.springbootexceptionhttps.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.onpassive.springbootexceptionhttps.emp.Employee;
import com.onpassive.springbootexceptionhttps.emp.EmployeeRepository;

@Service
public class CustomUserServiceDetailes  implements UserDetailsService{

	@Autowired
	EmployeeRepository employeeRepository;
	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {

		Employee detailes=employeeRepository.findByEmailId(emailId);
		System.out.println("detailes ::"+detailes);
		return new org.springframework.security.core.userdetails.User(detailes.getEmailId(),detailes.getPassword(),new ArrayList<>());
	}

}
