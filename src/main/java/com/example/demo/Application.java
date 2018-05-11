package com.example.demo;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.service.interfaces.IRoleService;
import com.example.demo.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {

	private final IRoleService roleService;
	private final IUserService userService;

	@Autowired
	public Application(IRoleService roleService, IUserService userService) {
		this.roleService = roleService;
		this.userService = userService;
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	 CommandLineRunner start(){
		return args -> {
			Role adminRole = new Role();
			adminRole.setName("ROLE_ADMIN");

			roleService.save(adminRole);

			Role userRole = new Role();
			userRole.setName("ROLE_USER");

			roleService.save(userRole);

			User user = new User();
			user.setLogin("a");
			user.setPassword(new BCryptPasswordEncoder().encode("a"));
			user.setRole(adminRole);

			userService.save(user);
        };
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
