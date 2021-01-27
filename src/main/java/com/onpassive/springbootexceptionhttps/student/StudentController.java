package com.onpassive.springbootexceptionhttps.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@GetMapping("/get-students")
    public ResponseEntity<List<StudentModel>> getAllEmployees() {
 
        List<StudentModel> employees = studentService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
 
    @PostMapping("/student")
    public ResponseEntity<StudentModel> saveEmployee(@RequestBody StudentModel employee) {
 
    	StudentModel emp = studentService.addStudent(employee);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }
 
    @PutMapping("/student")
    public ResponseEntity<StudentModel> updateEmployee(@RequestBody StudentModel employee) {
 
    	StudentModel emp = studentService.editStudent(employee);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }
 
    @DeleteMapping("/student")
    public ResponseEntity<String> deleteEmployee(@RequestParam(name = "employeeId") Integer employeeId) {
 
    	studentService.deleteStudent(employeeId);
        return new ResponseEntity<>("Employee with ID :" + employeeId + " deleted successfully", HttpStatus.OK);
    }
}
