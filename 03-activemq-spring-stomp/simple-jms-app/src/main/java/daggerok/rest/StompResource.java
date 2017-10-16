package daggerok.rest;

import daggerok.domain.Message;
import daggerok.messaging.MessageProducer;
import daggerok.props.MessageQueueProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class StompResource {

  final MessageQueueProperties props;
  final MessageProducer messageProducer;

  @MessageMapping("/send-message")
  public void message(final Message message) {
    log.info("received {}", message);
    messageProducer.send(message);
  }

  @CrossOrigin
  @GetMapping("/api/v1/stomp")
  public MessageQueueProperties.Stomp stomp() {
    return props.getStomp();
  }
}
