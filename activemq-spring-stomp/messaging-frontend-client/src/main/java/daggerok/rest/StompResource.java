package daggerok.rest;

import daggerok.props.MessagingProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StompResource {

  final MessagingProperties props;

  @CrossOrigin
  @GetMapping("/api/v1/stomp")
  public MessagingProperties.Stomp stomp() {
    return props.getService().getStomp();
  }
}
