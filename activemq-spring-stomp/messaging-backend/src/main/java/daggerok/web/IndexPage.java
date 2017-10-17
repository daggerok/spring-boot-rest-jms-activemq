package daggerok.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

@RestController
public class IndexPage {

  @GetMapping("/")
  public List<Map<String, String>> index() {
    return asList(
        Collections.singletonMap("GET", "/api/messages"),
        Collections.singletonMap("POST", "/api/v1/messages")
    );
  }
}
