package com.daggerok.rest2activemq;

import java.io.File;
import java.util.logging.Logger;
import javax.jms.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.jms.annotation.*;
import org.springframework.jms.config.*;
import org.springframework.jms.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;

@EnableJms
@SpringBootApplication
public class Application {
	static Logger logger = Logger.getLogger(Application.class.getName());
	
	static final String DESTINATION = "from-rest-to-jms-mailbox";
	
	@Description("this is where we start")
    public static void main(String[] args) {
        // Clean out any ActiveMQ data from a previous run
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    @Description("where are should be defined bean named 'myJmsContainerFactory' :)")
    JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }
    
    @RestController
    @RequestMapping(produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    @Description("producing message. for testing run: curl localhost:8080/send/Hello,%20World!")
    static class JmsMessageService {
    	@Autowired
    	JmsTemplate jmsTemplate;
    	
    	@RequestMapping("/send/{message}")
    	String send(@PathVariable("message") String message) {
            jmsTemplate.send(DESTINATION, new MessageCreator() {
            	@Override public Message createMessage(Session session) throws JMSException {
            		return session.createTextMessage(message); }});
            
    		return String.format("message '%s' sent.\n", message);
    	}
    }
    
    @Component
    @Description("simple receiver-listener, it consums any messages from ${DESTINATION}")
    static class Receiver {
        @JmsListener(destination = DESTINATION, containerFactory = "myJmsContainerFactory")
        void receive(String message) {
            logger.info(String.format("received new message %s: '%s'", DESTINATION, message));
        }
    }
}
