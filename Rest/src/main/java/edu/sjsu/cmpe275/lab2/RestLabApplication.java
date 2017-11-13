package edu.sjsu.cmpe275.lab2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RestLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestLabApplication.class, args);
	}
}
