package daggerok.rest;

import daggerok.props.MessageQueueProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class StompResource {

  final MessageQueueProperties props;

  @GetMapping("/api/v1/stomp-url")
  public Map send() {
    return Collections.singletonMap("stomp-url", props.getStompUrl());
  }
}
