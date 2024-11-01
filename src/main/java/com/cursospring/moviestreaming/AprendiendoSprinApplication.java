package com.cursospring.moviestreaming;

import com.cursospring.moviestreaming.model.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AprendiendoSprinApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AprendiendoSprinApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal();

		principal.muestraMenu();


	}
}


