package com.onpassive.springbootexceptionhttps.emp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;


	@Size(min = 4, max = 45, message = "emp name must 4 greater then or less then 45")
	@Column(name = "emp_name", nullable = false)
	private String empName;

	@NotEmpty(message = "emp phone Number is required")
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@Column(name = "email", nullable = false)
	@NotEmpty(message = "emp email id is required")
	@Email(message = "email should be a valid email")
	private String emailId;

	@Column(name = "password", nullable = false)
	@NotEmpty(message = "emp password is required")
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [id=");
		builder.append(id);
		builder.append(", empName=");
		builder.append(empName);
		builder.append(", phoneNumber=");
		builder.append(phoneNumber);
		builder.append(", emailId=");
		builder.append(emailId);
		builder.append(", password=");
		builder.append(password);
		builder.append("]");
		return builder.toString();
	}

	public Employee(long id,
			@Size(min = 4, max = 45, message = "emp name must 4 greater then or less then 45") String empName,
			@NotEmpty(message = "emp phone Number is required") String phoneNumber,
			@NotEmpty(message = "emp email id is required") @Email(message = "email should be a valid email") String emailId,
			@NotEmpty(message = "emp password is required") String password) {
		super();
		this.id = id;
		this.empName = empName;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.password = password;
	}

	public Employee() {
		super();
	}

	

	
}