package services.objex;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@Slf4j
public class RabbitMQSender {

    public static final String TOPIC = "objex";

    public static void main(String[] args) {
        SpringApplication.run(RabbitMQSender.class, args);
    }

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Bean
    public Queue objexQueue() {
        return new Queue(TOPIC, false);
    }

    @GetMapping("/m/{m}")
    public void send(@PathVariable String m) {
        log.info("Message sent : {}", m);

        rabbitTemplate.convertAndSend(TOPIC, m);
    }
}
