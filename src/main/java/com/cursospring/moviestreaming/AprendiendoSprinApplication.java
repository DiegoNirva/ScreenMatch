package com.cursospring.moviestreaming;

import com.cursospring.moviestreaming.model.Principal;
import com.cursospring.moviestreaming.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AprendiendoSprinApplication implements CommandLineRunner {

	@Autowired
	private SeriesRepository seriesRepository;

	public static void main(String[] args) {
		SpringApplication.run(AprendiendoSprinApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal(seriesRepository);

		principal.muestraMenu();


	}
}


