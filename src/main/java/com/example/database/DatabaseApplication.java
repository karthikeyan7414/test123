package com.example.database;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class DatabaseApplication implements CommandLineRunner{

	public static void main(String[] args) {
		 ApplicationContext ax = SpringApplication.run(DatabaseApplication.class, args);
		 
		
		
	}

	@Autowired
	private JobLauncher launcher;
	
	@Autowired
	private Job testJob;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		JobParameters param = new JobParametersBuilder().addLong("start-->", System.currentTimeMillis()).toJobParameters();
				
		
		launcher.run(testJob, param);
		
	}

	
}
