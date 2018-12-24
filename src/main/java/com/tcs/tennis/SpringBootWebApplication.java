package com.tcs.tennis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * Tennis Game Configuration file 
 * 
 *
 */
@Configuration
@SpringBootApplication
@ComponentScan(basePackages = { "com.tcs.tennis" })
public class SpringBootWebApplication 
{
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}

}
