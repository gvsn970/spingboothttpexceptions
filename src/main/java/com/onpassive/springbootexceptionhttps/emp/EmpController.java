package com.onpassive.springbootexceptionhttps.emp;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.onpassive.springbootexceptionhttps.exe.ErrorDetails;
import com.onpassive.springbootexceptionhttps.exe.ResourceNotFoundException;
import com.onpassive.springbootexceptionhttps.util.JwtUtil;

@RestController


//@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class EmpController {
	@Autowired
	private EmpService empService;

	@Autowired
	Environment environment;

	@Autowired
	JwtUtil util;

	@Autowired
	AuthenticationManager authenticationManager;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {

		return empService.findAll();
	}

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new Exception("inavalid username/password");
		}
		return util.generateToken(authRequest.getUsername());
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {

		return empService.findById(employeeId);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> createUser(@Valid @RequestBody Employee user) throws IOException {
		Employee existEmp = empService.findByEmailId(user.getEmailId());
		System.out.println("existEmp :::::::::::" + existEmp);
		Employee employee = null;

		if (existEmp == null) {
			employee = empService.save(user);
			if (employee.getId() != 0) {
				// ResponseEntity<?> status = fileupload(employee.getId(), file);
				// System.out.println("status :::::::::::"+status.get);
			}
			return new ResponseEntity<>(employee, HttpStatus.OK);
		} else {
			ErrorDetails errorDetails = new ErrorDetails(new Date(), "Emp Mail Id Already Exist",
					"pls give another mail id");
			return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
		}

	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> 
			errors.put(error.getField(), error.getDefaultMessage()));
		
		return errors;
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@Valid @PathVariable(value = "id") Long employeeId,
			@RequestBody Employee employeeDetails) throws ResourceNotFoundException {

		return empService.updatedEmployee(employeeId, employeeDetails);
		// return ResponseEntity.ok(updatedEmployee);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginEmployee(@RequestParam String email, @RequestParam String password) {
		List<Object[]> employee = empService.login(email, password);
		if (employee != null && !employee.isEmpty()) {
			return new ResponseEntity<>(employee, HttpStatus.OK);
		} else {
			ErrorDetails errorDetails = new ErrorDetails(new Date(), "Invalid User ...", "plas sgive another mail id");
			return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> deleteUser(@PathVariable("id") long userId) throws ResourceNotFoundException {
		// Employee existingUser = ((Object)
		// this.empService.findById(userId)).orElseThrow(() - > new
		// ResourceNotFoundException("User not found with id :" + userId));
		return this.empService.delete(userId);

	}

	@RequestMapping(value = "fileupload", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> fileupload(@Valid @RequestParam long l, @RequestParam("files") MultipartFile[] uploadFile)
			throws IOException {

		EmpFileModel uploadModel = null;
		if (uploadFile != null) {

			String UPLOADED_FOLDER = null;
			String EMP_FILE_FOLDER = environment.getProperty("app.empuploaddir");
			String fileName = null;
			String filePath = null;
			EMP_FILE_FOLDER = EMP_FILE_FOLDER + "EMP";
			// MultipartFile file = (MultipartFile) uploadFile;
			// BufferedImage image = ImageIO.read(file.getInputStream());

			CreateDirectoryResponseDTO response = empService.createDirectroy(EMP_FILE_FOLDER);
			System.err.println("response.getUploadPath() :::::::::::" + response.getUploadPath());
			if (uploadFile.length > 1) {
				boolean allUploadStatus = true;
				for (MultipartFile file_object : Arrays.asList(uploadFile)) {
					UPLOADED_FOLDER = response.getUploadPath();
					fileName = file_object.getOriginalFilename();
					System.err.println("fileName :::::::::::" + fileName);
					Date date = new Date();
					int lastDot = fileName.lastIndexOf('.');
					System.err.println("lastDot :::::::::::" + lastDot);
					fileName = fileName.substring(0, lastDot) + "_" + date.getTime() + fileName.substring(lastDot);
					filePath = Paths.get(UPLOADED_FOLDER, fileName).toString();
					uploadModel = new EmpFileModel();
					uploadModel = new EmpFileModel();
					uploadModel.setFileName(fileName);
					uploadModel.setFileLocation(filePath);
					uploadModel.setCreatedBy(1);
					uploadModel.setEmpId(l);
					// uploadModel.setFileType(FilenameUtils.getExtension(fileName));
					uploadModel.setFileSize(file_object.getSize() + " bytes");
					if (empService.saveFileToDisk(file_object, UPLOADED_FOLDER, fileName, filePath)) {
						EmpFileModel model = empService.fileUpload(uploadModel);
					} else {
						allUploadStatus = false;
					}
				}
				if (allUploadStatus) {
					ErrorDetails errorDetails = new ErrorDetails(new Date(), "File uploaded success  ...",
							"plas sgive another mail id");
					return new ResponseEntity<>(errorDetails, HttpStatus.OK);
				} else {
					ErrorDetails errorDetails = new ErrorDetails(new Date(), "File Uploading Failure  ...",
							"plas sgive another mail id");
					return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
				}

			} else if (uploadFile.length == 1) {
				MultipartFile file_object = uploadFile[0];
				UPLOADED_FOLDER = response.getUploadPath();
				fileName = file_object.getOriginalFilename();
				System.err.println("fileName :::::::::::" + fileName);
				Date date = new Date();
				int lastDot = fileName.lastIndexOf('.');
				System.err.println("lastDot :::::::::::" + lastDot);
				fileName = fileName.substring(0, lastDot) + "_" + date.getTime() + fileName.substring(lastDot);
				filePath = Paths.get(UPLOADED_FOLDER, fileName).toString();
				System.err.println("filePath :::::::::::" + filePath);
				uploadModel = new EmpFileModel();
				uploadModel = new EmpFileModel();
				uploadModel.setFileName(fileName);
				uploadModel.setFileLocation(filePath);
				uploadModel.setCreatedBy(1);
				uploadModel.setEmpId(l);
				System.err.println("uploadModel :::::::::::" + uploadModel);
				uploadModel.setFileType(FilenameUtils.getExtension(fileName));
				// System.err.println("FilenameUtils.getExtension(fileName)
				// :::::::::::"+FilenameUtils.getExtension(fileName));
				uploadModel.setFileSize(file_object.getSize() + " bytes");
				if (empService.saveFileToDisk(file_object, UPLOADED_FOLDER, fileName, filePath)) {
					EmpFileModel model = empService.fileUpload(uploadModel);
					if (model != null) {
						ErrorDetails errorDetails = new ErrorDetails(new Date(), "File uploaded success  ...",
								"plas sgive another mail id");
						return new ResponseEntity<>(errorDetails, HttpStatus.OK);
					}
				} else {
					ErrorDetails errorDetails = new ErrorDetails(new Date(), "File Uploading Failure  ...",
							"plas sgive another mail id");
					return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
				}
			} else {
				ErrorDetails errorDetails = new ErrorDetails(new Date(), "File Uploading Failure  ...",
						"plas sgive another mail id");
				return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
			}
			ErrorDetails errorDetails = new ErrorDetails(new Date(), "File Uploading Failure  ...",
					"plas sgive another mail id");
			return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
		}
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "File Uploading Failure  ...",
				"plas sgive another mail id");
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/page/{pageNo}/{pageSize}")
	public List<Employee> getPaginatedCountries(@PathVariable int pageNo, @PathVariable int pageSize) {

		return empService.findPaginated(pageNo, pageSize);
	}

}
