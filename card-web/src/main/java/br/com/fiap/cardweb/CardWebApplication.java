package br.com.fiap.cardweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class CardWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardWebApplication.class, args);
	}

}
