package com.example.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/hello")
public class DatabaseController {

	
	
	
	@PostMapping(value = "/file")
	public void getFile(@RequestParam  MultipartFile file) throws IOException {
		
		
		
		String filename = file.getOriginalFilename();
		
		 File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+filename);
		file.transferTo(convFile);
		 
		 System.out.println(convFile.getAbsolutePath());
		
		 
		
		
		
		
		
		
		
	}
	
}
