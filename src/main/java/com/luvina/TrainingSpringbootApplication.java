package com.luvina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * main method running spring boot application
 */
@SpringBootApplication
public class TrainingSpringbootApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TrainingSpringbootApplication.class, args);
	}
}
