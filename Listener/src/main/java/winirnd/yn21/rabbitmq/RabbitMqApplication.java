package winirnd.yn21.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMqApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/wini");
		SpringApplication.run(RabbitMqApplication.class, args);
	}

}
