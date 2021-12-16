package winirnd.yn21.rabbitmq.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
class RabbitConfig {

	private static final String queueName = "queueC";
	private static final String topicExchangeName = "sensor";
	
	@Bean
	Queue queue() {
		return new Queue(queueName, true);
	}
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}
	
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("sample.sensor.C");
	}
	
	@Bean
	   public RestTemplate restTemplate() {
	      
	      HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
	      factory.setReadTimeout(5000); // 읽기시간초과, ms 
	      factory.setConnectTimeout(3000); // 연결시간초과, ms 
	      
	      HttpClient httpClient = HttpClientBuilder.create() 
	            .setMaxConnTotal(100) // connection pool 적용 
	            .setMaxConnPerRoute(5) // connection pool 적용 
	            .build();
	      
	      factory.setHttpClient(httpClient); // 동기실행에 사용될 HttpClient 세팅 
	      RestTemplate restTemplate = new RestTemplate(factory); 
	      
	      return restTemplate;
	   }
}
