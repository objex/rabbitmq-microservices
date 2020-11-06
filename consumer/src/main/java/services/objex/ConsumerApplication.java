package services.objex;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
public class ConsumerApplication {

	public static final String TOPIC = "objex";

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Autowired
	public MsgRepo msgRepo;

	@Bean
	public Queue objexQueue() {
		return new Queue(TOPIC, false);
	}

	@RabbitListener(queues = TOPIC)
	public void listen(String in) {
		log.info("Message read from objex : " + in);
		msgRepo.save(new Msg(in));
	}

	@GetMapping("/put/{p}")
	public String put(@PathVariable String p) {
		Msg save = msgRepo.save(new Msg(p));
		return save.toString();
	}

	@GetMapping("/get")
	public Iterable<Msg> get() {
		Iterable<Msg> all = msgRepo.findAll();
		msgRepo.deleteAll();
		return all;
	}

}
