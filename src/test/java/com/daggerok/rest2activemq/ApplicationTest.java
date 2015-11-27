package com.daggerok.rest2activemq;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import javax.jms.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class ApplicationTest {
	@Autowired
	ConfigurableApplicationContext context;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@After
	public void after() {
		context.close();
	}
	
	@Test
	public void testJms() throws InterruptedException {
		assertNotNull("jmsTemplate is null", jmsTemplate);
		
		jmsTemplate.send(Application.DESTINATION, new MessageCreator() {
        	@Override public Message createMessage(Session session) throws JMSException {
        		return session.createTextMessage("test message"); }});
		
		TimeUnit.SECONDS.sleep(1);
		System.out.println("verify INFO log message \"received new message from-rest-to-jms-mailbox: 'test message'\" in console");
		TimeUnit.SECONDS.sleep(3);
	}
}
