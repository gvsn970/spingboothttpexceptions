package com.onpassive.springbootexceptionhttps.emp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>,PagingAndSortingRepository<Employee,Long> {

	Employee findByEmailId(String emailId);

}
