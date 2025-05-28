package com.user.demo.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class User {

	@Id
	private String id;

	@NotBlank
	private String name;

	@Min(value = 18, message = "Age must be at least 18")
	private int age;

	@NotBlank
	@Email
	private String email;

	@NotBlank(message = "Country is required")
	private String country;

	private String phone;

	
}