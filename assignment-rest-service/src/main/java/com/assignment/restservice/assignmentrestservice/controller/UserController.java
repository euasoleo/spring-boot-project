package com.assignment.restservice.assignmentrestservice.controller;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.restservice.assignmentrestservice.model.User;
import com.assignment.restservice.assignmentrestservice.payload.UploadFileResponse;
import com.assignment.restservice.assignmentrestservice.repository.UserRepository;
import com.assignment.restservice.assignmentrestservice.service.FileStorageService;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;


@RestController
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(value ="/users")
	public List<User> retrieveAllValidSalaryUsers() {

		///users that output valid users with valid salary (0 <= salary <= 4000) only
		return userRepository.findBySalaryGreaterThanEqualAndSalaryLessThanEqual(0, 4000);
	}
	
	@PostMapping("/users")
    public UploadFileResponse newUsersCSV(@RequestParam("file") MultipartFile file) {
	  
        String fileName = fileStorageService.storeFile(file);
        logger.info("Storing file, Name:"+fileName);
        
        try (
                Reader reader = Files.newBufferedReader(Paths.get(fileName));
        		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();//to skip csv header
            ) {
                String[] nextRecord;
                while ((nextRecord = csvReader.readNext()) != null) 
                {
                	String name = nextRecord[0];
                	double salary = Double.parseDouble(nextRecord[1]);

                    User newUser = new User();
                    newUser.setName(name);
                    newUser.setSalary(salary);
                    
                    userRepository.save(newUser);
                    
                }
            } 
        	catch (IOException e) {
				e.printStackTrace();
			}
	        catch (Exception e) {
				e.printStackTrace();
			}
        
        return new UploadFileResponse(fileName,
                file.getContentType(), file.getSize());
}
}
