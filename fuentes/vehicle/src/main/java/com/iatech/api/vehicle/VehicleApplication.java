package com.iatech.api.vehicle;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.iatech.api.vehicle.util.Constants;

@SpringBootApplication
public class VehicleApplication {

	public static void main(String[] args) {
		
		String log4jfile = System.getProperty("dir.config");
		System.out.println(">>>>>>> " + log4jfile);
		LoggerContext context = (LoggerContext) LogManager.getContext(false);
		File file = new File(log4jfile + Constants.LOG4J_FILE);
		context.setConfigLocation(file.toURI());


		
		SpringApplication.run(VehicleApplication.class, args);
	}

}

