package com.onpassive.springbootexceptionhttps.Student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	public List<StudentModel> getAllEmployees() {
		 
        return studentRepository.findAll();
    }
 
    public StudentModel addStudent(StudentModel employee) {
 
        Department dept = departmentRepository.findById(employee.getDepartment().getId()).orElse(null);
        if (null == dept) {
            dept = new Department();
        }
        dept.setDeptName(employee.getDepartment().getDeptName());
        employee.setDepartment(dept);
        return studentRepository.save(employee);
    }
 
    public StudentModel editStudent(StudentModel entity) {
 
        return studentRepository.save(entity);
    }
 
    public void deleteStudent(Integer id) {
 
    	studentRepository.deleteById(id);
    }
}
