package com.digitinary.DStore;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Log4j2
public class DStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(DStoreApplication.class, args);
		log.info("Log4j2 from Lombok is working!");

	}
}
