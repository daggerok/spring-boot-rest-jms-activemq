package daggerok.web;

import daggerok.props.MessagingProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexPage {

  @RestController
  @RequiredArgsConstructor
  public static class ConfigResource {

    final MessagingProperties props;

    @GetMapping("/api/v1/config")
    public MessagingProperties.Service getConfig() {
      return props.getService();
    }
  }

  @GetMapping({ "", "/", "/404" })
  public String index() {
    return "index";
  }
}
