package com.onpassive.springbootexceptionhttps.emp;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onpassive.springbootexceptionhttps.exe.ResourceNotFoundException;

@Service
public interface EmpService {

	public List<Object[]> login(String email, String password);

	public List<Employee> findAll();

	public  ResponseEntity<Employee> findById(Long employeeId) throws ResourceNotFoundException;

	public Employee save(Employee employee);

	public ResponseEntity<Employee> updatedEmployee(Long employeeId,Employee employeeDetails) throws ResourceNotFoundException;

	public Employee findByEmailId(String emailId);

	public CreateDirectoryResponseDTO createDirectroy(String eMP_FILE_FOLDER);

	public boolean saveFileToDisk(MultipartFile file_object, String uPLOADED_FOLDER, String fileName, String filePath);

	public EmpFileModel fileUpload(EmpFileModel uploadModel);

	public ResponseEntity<Employee> delete(long userId) throws ResourceNotFoundException;

	//public Map<String, Boolean> delete(Long employeeId);
	
	public List<Employee> findPaginated(int pageNo, int pageSize);

	

	
}
