package com.onpassive.springbootexceptionhttps.emp;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepositoryImpl {
	
	@Autowired
	EmployeeRepository employeeRepository;

	
    @Autowired
    private EntityManager em;
    
	public List<Object[]> login(String email, String password) {
		//Employee empEmail=employeeRepository.findByEmailId(email);
		 Session session = em.unwrap(Session.class);
		String query="SELECT * FROM employees WHERE email='"+email+"' AND password='"+password+"' ";
		 Query  q= session.createNativeQuery(query);
			List<Object[]> list = q.list();
	        return list;
	}

	public EmpFileModel fileupload(EmpFileModel uploadModel) {
		Transaction tx = null;
		
		  Session session = em.unwrap(Session.class);
		  tx = session.beginTransaction();
	        session.persist(uploadModel);
	        tx.commit();
	        return uploadModel;
	}

}
