package com.onpassive.springbootexceptionhttps.file;

import org.springframework.web.multipart.MultipartFile;

public class RequestDTO {

	private String empName;
	private MultipartFile empPhoto;
	
	public RequestDTO() {
		super();
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public MultipartFile getEmpPhoto() {
		return empPhoto;
	}
	public void setEmpPhoto(MultipartFile empPhoto) {
		this.empPhoto = empPhoto;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequestDTO [empName=");
		builder.append(empName);
		builder.append(", empPhoto=");
		builder.append(empPhoto);
		builder.append("]");
		return builder.toString();
	}
	
	
}
