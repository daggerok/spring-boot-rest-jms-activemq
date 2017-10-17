package daggerok.messaging;

import daggerok.MessagingBackend;
import daggerok.domain.Message;
import daggerok.props.MessageQueueProperties;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

import static java.util.Collections.singletonList;

@EnableJms
@Configuration
@RequiredArgsConstructor
public class JmsConfig {

  final MessageQueueProperties activemq;

  @Bean
  @Primary
  public ConnectionFactory connectionFactory() {
    val factory = new ActiveMQConnectionFactory(activemq.getUser(), activemq.getPassword(), activemq.getUrl());
    factory.setTrustedPackages(singletonList(Message.class.getPackage().getName()));
    return factory;
  }

  /**
   * Enable listener endpoint annotations.
   * To enable support for @JmsListener.
   */
  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
    val factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory());
    factory.setClientId(MessagingBackend.class.getName());
    factory.setConcurrency("2-10");
    return factory;
  }
}
