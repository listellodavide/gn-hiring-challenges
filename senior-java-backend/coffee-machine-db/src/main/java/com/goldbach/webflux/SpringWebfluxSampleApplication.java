package com.goldbach.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Davide Listello
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.goldbach.webflux")
public class SpringWebfluxSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxSampleApplication.class, args);
	}

}
