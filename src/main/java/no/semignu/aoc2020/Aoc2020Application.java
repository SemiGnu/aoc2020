package no.semignu.aoc2020;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
//@SpringBootApplication
public class Aoc2020Application {

	public static void main(String[] args) {

//		SpringApplication.run(Aoc2020Application.class, args);
		var test = Boolean.parseBoolean(System.getenv("TEST"));
		Day today = new Day15();
		log.info("\n{}:\nPart 1:\n{}\nPart 2:\n{}", today.getClass().getSimpleName(),today.day1(test),today.day2(test));
	}

}