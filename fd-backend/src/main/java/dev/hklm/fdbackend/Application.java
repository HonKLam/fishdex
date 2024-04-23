package dev.hklm.fdbackend;

import dev.hklm.fdbackend.Entities.Catch;
import dev.hklm.fdbackend.Entities.Diary;
import dev.hklm.fdbackend.Entities.Fish;
import dev.hklm.fdbackend.Entities.Fishdex;
import dev.hklm.fdbackend.Entities.User;
import dev.hklm.fdbackend.Entities.Userboard;
import dev.hklm.fdbackend.Repositories.DiaryRepository;
import dev.hklm.fdbackend.Repositories.FishdexRepository;
import dev.hklm.fdbackend.Repositories.UserboardRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

	// Hier Tabellen / Repos der DB mit Daten füllen
	@Bean
	public CommandLineRunner initRepos(FishdexRepository fishdexRepository, DiaryRepository diaryRepository, UserboardRepository userboardRepository) {
		return (args) -> {

			Fish f1 = new Fish( 0, "Koenigslachs", "fresh- and saltwater", "live bait", true, "tasty", 1L );
			Fish f2 = new Fish(0, "Neon", "freshwater", "use a net", false, "colorful", 2L );
			Fish f3 = new Fish(0, "Weiser Hai", "saltwater", "bookworms", false, "smart boi", 3L);
			List<Fish> fishList = Arrays.asList(f1, f2, f3);

			Fishdex fishdex = new Fishdex(fishList);
			fishdexRepository.save(fishdex);


			Catch c1 = new Catch("Dresden", 12.3, "Sehr cooler Tag", ZonedDateTime.parse("2016-10-05T08:20:10+05:30[Asia/Kolkata]"), f2.getId(), 1L);
			Catch c2 = new Catch("BaWü", 15.0, "Ich wurde von Finn gefangen.", ZonedDateTime.parse("2024-04-02T01:10:22+02[Europe/Berlin]"), f1.getId(), 2L);
			Catch c3 = new Catch("Ostsee", 20.3, "noice.", ZonedDateTime.of(2015, 11, 30, 23, 45, 59, 0, ZoneId.of("GMT+02:00")), f2.getId(), 3L);
			List<Catch> catchList = Arrays.asList(c1, c2, c3);

			Diary diary = new Diary(catchList);
			diaryRepository.save(diary);



			User u1 = new User("Sweden", "Blahaj", "SHAAA", 3, 1L, 1, 1);
			User u2 = new User("Super Earth", "John Helldiver", "For Democracy!", 1337, 2L, 2, 2);
			User u3 = new User("Minecraft", "Steve", "Master Baiter", 0, 3L, 3, 3);
			List<User> userList = Arrays.asList(u1, u2, u3);

			Userboard userboard = new Userboard(userList);
			userboardRepository.save(userboard);

		};
	}
}
