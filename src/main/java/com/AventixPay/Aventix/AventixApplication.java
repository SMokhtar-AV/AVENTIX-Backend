package com.AventixPay.Aventix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.AventixPay.Aventix.entities")  // 🔥 Scanne les entités
@EnableJpaRepositories(basePackages = "com.AventixPay.Aventix.repositories")
public class AventixApplication {

	public static void main(String[] args) {
		SpringApplication.run(AventixApplication.class, args);
	}

}
