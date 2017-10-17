package daggerok.rest;

import daggerok.domain.Message;
import daggerok.messaging.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
@RequiredArgsConstructor
public class MessageResource {

  final MessageProducer messageProducer;

  @ResponseStatus(ACCEPTED)
  @PostMapping("/api/v1/messages")
  public Message send(@RequestBody @Validated final Message message) {
    messageProducer.send(message);
    return message;
  }
}
