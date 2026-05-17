package com.example.necromancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NecromancerWorkforceManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NecromancerWorkforceManagerApplication.class, args);
	}

}
