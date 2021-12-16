package winirnd.yn21.rabbitmq.rest;


import org.apache.tomcat.util.json.ParseException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RabbitController {

	@Autowired
	RabbitTemplate rabbitTemplate;
	@Autowired
	RestTemplate template;

	private String message = null;
	

	@RabbitListener(queues = "queueA")
	public void workerOneMessage(final Message message) throws JsonMappingException, JsonProcessingException, ParseException {
		System.out.println("1" + new String(message.getBody()));
		this.message = new String(message.getBody());
		String url = "http://192.168.33.131:8888/hhw/test.do";
		template.postForObject(url, this.message, String.class);
	}

	@RabbitListener(queues = "queueB")
	public void listeningTest(final String message) {
		log.info("2" + message);
		rabbitTemplate.convertAndSend("sensor", "sample.sensor.C", message);

	}

}
