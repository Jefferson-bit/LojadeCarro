package com.crash.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crash.domain.FileDB;
import com.crash.domain.message.ResponseFile;
import com.crash.resources.utils.URL;
import com.crash.service.FileService;

@RestController
@RequestMapping(value = "/file")
public class FileResource {	

	@Autowired
	private FileService service;

	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("files") MultipartFile[] files) throws IOException {
		String message = "";
		List<String> fileNames = new ArrayList<>();
		Arrays.asList(files).stream().forEach(file -> {
			 try {
				service.store(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		fileNames.add(file.getOriginalFilename());
		});
		message = "Uploaded the file successfully: " +  message;
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	
	@GetMapping("/files")
	  public ResponseEntity<List<ResponseFile>> getListFile() {
	    List<ResponseFile> files = service.getAllFiles().map(dbFile ->  {
	    String fileDownloadUri = ServletUriComponentsBuilder
	    	.fromCurrentContextPath()
	    	.path("/file/files/")
	    	.path(dbFile.getId())
	         .toUriString();
	    return new ResponseFile(
	          dbFile.getNome(),
	          fileDownloadUri,
	          dbFile.getType(),
	          dbFile.getData().length);
	    }).collect(Collectors.toList());
	    return ResponseEntity.status(HttpStatus.OK).body(files);
	  }
	
	  @GetMapping("/car")
	  public ResponseEntity<byte[]> getFileName(@RequestParam("nome") String nome) {
		String decodeName = URL.decodeParam(nome);
	    FileDB fileDB = service.fileName(decodeName);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getNome() + "\"")
	        .body(fileDB.getData());
	 
	  }
}
