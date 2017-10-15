package com.daggerok.rest2activemq;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

    @Autowired
    JmsTemplate jmsTemplate;

    @Test
    public void testJms() throws InterruptedException {
        assertNotNull("jmsTemplate is null", jmsTemplate);

        jmsTemplate.send(Application.DESTINATION, session ->
            session.createTextMessage("test message"));

        TimeUnit.SECONDS.sleep(1);
        System.out.println("verify INFO log message: \"received new message from-rest-to-jms-mailbox: 'test message'\" in console");
        TimeUnit.SECONDS.sleep(3);
    }
}
