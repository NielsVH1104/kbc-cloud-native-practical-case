package com.ezgroceries;

import com.ezgroceries.configuration.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AppConfig.class)
@ComponentScan("com.ezgroceries")
@EnableFeignClients
public class EZGroceriesShoppingListApplication {

	public static void main(String[] args) {
		SpringApplication.run(EZGroceriesShoppingListApplication.class, args);
	}

}
