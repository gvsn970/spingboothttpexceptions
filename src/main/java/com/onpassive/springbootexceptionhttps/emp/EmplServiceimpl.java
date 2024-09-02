package com.onpassive.springbootexceptionhttps.emp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.onpassive.springbootexceptionhttps.exe.ResourceNotFoundException;

@Service
public class EmplServiceimpl implements EmpService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired 
	EmployeeRepositoryImpl employeeRepositoryImpl;

	@Override
	public List<Object[]> login(String email, String password) {
		return employeeRepositoryImpl.login(email,password);
	}
	

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public ResponseEntity<Employee> findById(Long employeeId) throws ResourceNotFoundException {
		
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@Override
	public Employee save(Employee employee) {

		return employeeRepository.save(employee);
	}

	@Override
	
	public ResponseEntity<Employee> updatedEmployee(Long employeeId, Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		
		employee.setEmpName(employeeDetails.getEmpName());
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setPhoneNumber(employeeDetails.getPhoneNumber());
		employee.setPassword(employeeDetails.getPassword());
		
		return ResponseEntity.ok().body(employeeRepository.save(employee));
	}

	@Override
	public Employee findByEmailId(String emailId) {
		return employeeRepository.findByEmailId(emailId);
	}

	@Override
	public CreateDirectoryResponseDTO createDirectroy(String eMP_FILE_FOLDER) {

		CreateDirectoryResponseDTO userResponseDTO = new CreateDirectoryResponseDTO();
		try {
			if (eMP_FILE_FOLDER != null ) {
				File file = new File(eMP_FILE_FOLDER );
				if (!file.exists()) {
					if (file.mkdir()) {
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setStatusMessage("Folder Directory is created");
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setStatusMessage("Failed to create  Folder directory");
					}
				} else {
					userResponseDTO.setStatusCode(2);
					userResponseDTO.setStatusMessage("Folder Directory already exists");
				}
				File file1 = new File(file.getPath() +"/emp_file_uploads");
				if (!file1.exists()) {
					if (file1.mkdir()) {
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setStatusMessage("emp Directory is created");
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setStatusMessage("Failed to create  Emp directory");
					}
				} else {
					userResponseDTO.setStatusCode(2);
					userResponseDTO.setStatusMessage("Emp Directory already exists");
				}
				
				System.out.println(":file1 :"+file1.getPath());
				File file2 = new File(file1.getPath() + "/"+java.time.LocalDate.now());
				if (!file2.exists()) {
					if (file2.mkdir()) {
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setStatusMessage("User Directory is created");
						userResponseDTO.setUploadPath(file2.getPath());
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setStatusMessage("Failed to create  User directory");
					}
				} else {
					userResponseDTO.setStatusCode(2);
					userResponseDTO.setStatusMessage("User Directory already exists");
					userResponseDTO.setUploadPath(file2.getPath());
				}

			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setStatusMessage("All input fields are required");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println("In create directory:::::::::::::" + userResponseDTO.getStatusMessage() + "path::::::::::"
				+ userResponseDTO.getUploadPath());
		return userResponseDTO;
	}

	@Override
	public boolean saveFileToDisk(MultipartFile file_object, String uPLOADED_FOLDER, String fileName, String filePath) {
		try {
			byte[] bytes = file_object.getBytes();
			new File(uPLOADED_FOLDER + fileName);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			stream.write(bytes);
			stream.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public EmpFileModel fileUpload(EmpFileModel uploadModel) {
		return employeeRepositoryImpl.fileupload(uploadModel);
	}

	@Override
	public ResponseEntity<Employee> delete(long userId) throws ResourceNotFoundException {

		Employee employee = employeeRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + userId));
			employeeRepository.delete(employee);
	        return ResponseEntity.ok().body(employee);
	}

	@Override
	public List<Employee> findPaginated(int pageNo, int pageSize) {
		  Pageable paging = PageRequest.of(pageNo, pageSize,Sort.by("id").descending());
	        Page<Employee> pagedResult = employeeRepository.findAll(paging);

	        return pagedResult.toList();
	}



}
