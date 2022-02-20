package com.onpassive.springbootexceptionhttps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringbootexceptionhttpsApplication {

//	@Autowired
//	EmployeeRepository employeeRepository;
//
//	@PostConstruct
//	public void initUsers() {
//		List<Employee> users = Stream
//				.of(new Employee(101, "javatechie", "password", "javatechie@gmail.com", "9701316387"),
//						new Employee(102, "user1", "pwd1", "user1@gmail.com", "9701316387"),
//						new Employee(103, "user2", "pwd2", "user2@gmail.com", "9701316387"),
//						new Employee(104, "user3", "pwd3", "user3@gmail.com", "9701316387"))
//				.collect(Collectors.toList());
//		employeeRepository.saveAll(users);
//	}
	/// git commit

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowCredentials(true).allowedOrigins("*").allowedMethods("*")
						.allowedHeaders("*");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootexceptionhttpsApplication.class, args);
	}

}
