package daggerok.messaging;

import daggerok.props.MessageQueueProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class StompConfig extends AbstractWebSocketMessageBrokerConfigurer {

  final MessageQueueProperties props;

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    //config.enableSimpleBroker("/topic");
    config.enableStompBrokerRelay("/topic", "/queue", "jms.topic.messages")
          .setRelayHost(props.getServer().getHost())
          .setSystemHeartbeatReceiveInterval(30000)
          .setUserRegistryBroadcast("/topic/user-registry/broadcast")
          .setUserDestinationBroadcast("/topic/user-destination/broadcast")
    ;
    config.setApplicationDestinationPrefixes("/app");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/stomp")
            .setAllowedOrigins("*")
            .withSockJS()
//            .setSupressCors(true)
//            .setWebSocketEnabled(false)
    ;
  }
}
