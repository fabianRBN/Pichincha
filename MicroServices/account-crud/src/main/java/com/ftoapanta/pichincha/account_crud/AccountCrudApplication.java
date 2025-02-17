package com.ftoapanta.pichincha.account_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountCrudApplication.class, args);
	}

}
