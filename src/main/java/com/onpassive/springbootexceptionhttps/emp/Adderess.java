package com.onpassive.springbootexceptionhttps.emp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "adderess")
public class Adderess {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String adds;

	@ManyToOne
	@JoinColumn(name = "adds_fk")
	private Adderess emp_adds;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdds() {
		return adds;
	}

	public void setAdds(String adds) {
		this.adds = adds;
	}

	public Adderess getEmp_adds() {
		return emp_adds;
	}

	public void setEmp_adds(Adderess emp_adds) {
		this.emp_adds = emp_adds;
	}

	
	
}
