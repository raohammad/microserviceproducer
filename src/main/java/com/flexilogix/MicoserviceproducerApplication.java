package com.flexilogix;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.integration.annotation.Poller;

@SpringBootApplication
@EnableBinding(Source.class)
public class MicoserviceproducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicoserviceproducerApplication.class, args);
	}
	
	@Bean
	@InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1"))
	public MessageSource<Data> timerMessageSource() {
		return () -> MessageBuilder.withPayload(new Data(new Date().getTime()+"","Label")).build();
	}
}
