package com.crash.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.crash.domain.FileDB;
import com.crash.repositories.FileRepository;

@Service
public class FileService {

	@Autowired
	private FileRepository fileRepository;

	public FileDB store(MultipartFile file) throws IOException{
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
		return fileRepository.save(fileDB);
	}
	
	public FileDB getFile(String id) {
		 return fileRepository.findById(id).get();
	}
	
	public Stream<FileDB> getAllFiles() {
		 return fileRepository.findAll().stream();
	}

	public FileDB fileName(String nome) {
		 FileDB file = fileRepository.findByNomeIgnoreCase(nome);
		 return file;
	}
}
