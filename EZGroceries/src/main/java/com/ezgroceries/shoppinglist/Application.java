package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.configuration.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AppConfig.class)
@ComponentScan("com.ezgroceries.shoppinglist")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
