
package com.user.demo.mainApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "com.user.demo.controller", "com.user.demo.service", "com.user.demo.model",
		"com.user.demo.exception" })
@EnableMongoRepositories(basePackages = "com.user.demo.repository")
public class UserServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
