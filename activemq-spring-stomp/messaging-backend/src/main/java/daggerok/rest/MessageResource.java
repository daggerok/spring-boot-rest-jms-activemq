package daggerok.rest;

import daggerok.domain.Message;
import daggerok.messaging.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
@RequiredArgsConstructor
public class MessageResource {

  final MessageProducer messageProducer;

  @CrossOrigin
  @ResponseStatus(ACCEPTED)
  @PostMapping("/api/v1/messages")
  public Message send(@RequestBody @Validated final Message message) {
    messageProducer.send(message);
    return message;
  }
}
