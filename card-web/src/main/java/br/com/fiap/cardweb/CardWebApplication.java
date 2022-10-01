package br.com.fiap.cardweb;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.sql.SQLException;

@SpringBootApplication
@EnableWebMvc
public class CardWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardWebApplication.class, args);
	}

	//Exposição do protocolo TCP para compartilhar o banco de dados em memória/arquivo H2.
	//Com essa aplicação WEB rodando, a aplicação BATCH usará essa url e a inteface H2 para
	// suas transações do banco de dados.
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseaServer() throws SQLException {
		return Server.createTcpServer(
				"-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
	}
}
