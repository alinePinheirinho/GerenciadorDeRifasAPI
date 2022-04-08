package br.com.pinheirostec.gerenciadorrifaonline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories
public class GerenciadorRifaOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorRifaOnlineApplication.class, args);
	}

}
