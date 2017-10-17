package daggerok;

import daggerok.domain.Message;
import daggerok.domain.MessageRestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

@Slf4j
@Configuration
public class Testdata {

  @Bean
  public CommandLineRunner init(final MessageRestRepository repository) {

    if (repository.count() > 0) return args -> log.info("data a;ready exists.");

    return args -> Stream.of("test-1", "test-two")
                         .map(s -> new Message().setBody(s))
                         .forEach(repository::save);
  }
}
