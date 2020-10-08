package com.onpassive.springbootexceptionhttps.Student;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Department")

public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "deptName")
	private String deptName;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<StudentModel> employees;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<StudentModel> getEmployees() {
		return employees;
	}

	public void setEmployees(List<StudentModel> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Department [id=");
		builder.append(id);
		builder.append(", deptName=");
		builder.append(deptName);
		builder.append(", employees=");
		builder.append(employees);
		builder.append("]");
		return builder.toString();
	}

	public Department(Integer id, String deptName, List<StudentModel> employees) {
		super();
		this.id = id;
		this.deptName = deptName;
		this.employees = employees;
	}

	public Department() {
		super();
	}
	
	
	
}
