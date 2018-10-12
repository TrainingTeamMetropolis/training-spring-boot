package com.luvina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * main method running spring boot application
 */
@SpringBootApplication
public class TrainingSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingSpringbootApplication.class, args);
    }
}
