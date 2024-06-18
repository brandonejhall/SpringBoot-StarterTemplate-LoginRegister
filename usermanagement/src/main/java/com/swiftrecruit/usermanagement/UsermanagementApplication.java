package com.swiftrecruit.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "com.swiftrecruit.usermanagement" })
@EnableCaching
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class UsermanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsermanagementApplication.class, args);
		// System.out.println("Hello World");
	}

}
