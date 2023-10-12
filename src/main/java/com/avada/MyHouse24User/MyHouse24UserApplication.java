package com.avada.MyHouse24User;

import com.avada.MyHouse24User.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RabbitConfig.class)
public class MyHouse24UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyHouse24UserApplication.class, args);
	}

}
