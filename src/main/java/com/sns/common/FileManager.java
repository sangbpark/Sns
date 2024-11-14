package com.sns.common;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileManager  {
	public static final String FILE_UPLOAD_PATH = "C:\\Users\\qkrtk\\Desktop\\sangbae\\6_spring_project\\sns\\sns_workspace\\images/";
	
	public String uploadFile(MultipartFile file, String loginId) {
		String directoryName = loginId + "_" + System.currentTimeMillis();
		String filePath = FILE_UPLOAD_PATH + directoryName + "/";
		
		File directory = new File(filePath);
		if (directory.mkdir() == false) {
			log.info("[파일매니저 파일생성 실패] directory:{}", filePath);
			return null; 
		}
		
		
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
			return "/images/" + directoryName + "/" + file.getOriginalFilename();
		} catch (IOException e) {
			log.info("[파일매니저 파일생성 이미지 실패] imageName:{}", file.getOriginalFilename());
			return null; 
		}

	}
	
	public void deleteFile(String imagePath) {
		Path path= null;
		try {
		path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/", ""));
		} catch (InvalidPathException e) {
			log.info("[파일매니저 파일경로 문제] imagePath:{}", imagePath);
			return;
		}
		if (Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				log.info("[파일매니저 파일삭제] imagePath:{}", imagePath);
				return;
			}
		}
		
		path = path.getParent();
		if (Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				log.info("[파일매니저 폴더삭제] imagePath:{}", imagePath);
			}
		}
	}
}
