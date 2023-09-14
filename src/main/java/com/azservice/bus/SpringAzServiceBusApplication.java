package com.azservice.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

import com.azservice.bus.entity.EmailDetails;
import com.azservice.bus.service.EmailService;

@SpringBootApplication
@EnableJms

public class SpringAzServiceBusApplication implements CommandLineRunner {
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	EmailService emailService;

	private static final String QUEUE_NAME = "data-receive-queue";
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringAzServiceBusApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringAzServiceBusApplication.class, args);
	}

	
	  @Override
	  public void run(String... args) throws Exception {
		  // TODO Auto-generated method stub 
		  LOGGER.info("Sending message");
	  jmsTemplate.convertAndSend(QUEUE_NAME, "Hello Word");
	  
	  }
	  
	  @JmsListener(destination = QUEUE_NAME, containerFactory = "jmsListenerContainerFactory")
	  public void receiveMessage(String message) {
		  
	  LOGGER.info("Message received: {}", message);
	  EmailDetails details = new EmailDetails("siddhant.singh@neosalpha.com", message,"subject",null);
	  emailService.sendSimpleMail(details);
	  }
	 

}
