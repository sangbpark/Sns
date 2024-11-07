package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager  {
	public static final String FILE_UPLOAD_PATH = "C:\\Users\\qkrtk\\Desktop\\sangbae\\6_spring_project\\sns\\sns_workspace\\images/";
	
	public String uploadFile(MultipartFile file, String loginId) {
		if (file == null) return null;
		String directoryName = loginId + "_" + System.currentTimeMillis();
		String filePath = FILE_UPLOAD_PATH + directoryName + "/";
		
		File directory = new File(filePath);
		if (directory.mkdir() == false) {
			return null; 
		}
		
		
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
			return null; 
		}

		return "/images/" + directoryName + "/" + file.getOriginalFilename();
	}
}