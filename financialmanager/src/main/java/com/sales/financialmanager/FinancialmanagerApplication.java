package com.sales.financialmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FinancialmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialmanagerApplication.class, args);
	}

}
