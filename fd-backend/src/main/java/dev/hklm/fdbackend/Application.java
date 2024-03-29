package dev.hklm.fdbackend;

import dev.hklm.fdbackend.Entities.TestEntity;
import dev.hklm.fdbackend.Repositories.TestRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// Hier Tabellen / Repos der DB mit Daten füllen
	@Bean
	public CommandLineRunner initRepos(TestRepository testRepository) {
		return (args) -> {
			TestEntity e1 = new TestEntity("Lam", 20, "Dresden");
			TestEntity e2 = new TestEntity("Peter", 500, "Dresden");
			TestEntity e3 = new TestEntity("Finn", 1000, "Dresden");
			TestEntity e4 = new TestEntity("Finja", 3, "Dresden");
			TestEntity e5 = new TestEntity("Nico", 37, "Dresden");

			List<TestEntity> userList = Arrays.asList(e1, e2, e3, e4, e5);

			// in Repository speichern
			testRepository.saveAll(userList);
		};
	}

}
