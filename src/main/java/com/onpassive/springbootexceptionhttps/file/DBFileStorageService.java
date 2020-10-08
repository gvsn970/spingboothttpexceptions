package com.onpassive.springbootexceptionhttps.file;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DBFileStorageService {

	@Autowired
	private FileDBRepository fileDBRepository;

	public DBFileModel store(MultipartFile[] file) throws IOException {
		String fileName = null;
		DBFileModel fileDB=null;
		if (file.length > 1) {
			for (MultipartFile file_object : Arrays.asList(file)) {
				fileName = StringUtils.cleanPath(file_object.getOriginalFilename());
				fileDB = new DBFileModel(fileName, file_object.getContentType(), file_object.getBytes());
				fileDBRepository.save(fileDB);
			}
			return fileDB;
		} else if(file.length == 1){
			MultipartFile file_object = file[0];
			fileName = StringUtils.cleanPath(file_object.getOriginalFilename());
			DBFileModel FileDB = new DBFileModel(fileName, file_object.getContentType(), file_object.getBytes());
			return fileDBRepository.save(FileDB);

		}else {
			return null;
		}

	}

	public DBFileModel getFile(String id) {
		return fileDBRepository.findById(id).get();
	}

	public Stream<DBFileModel> getAllFiles() {
		return fileDBRepository.findAll().stream();
	}
}
