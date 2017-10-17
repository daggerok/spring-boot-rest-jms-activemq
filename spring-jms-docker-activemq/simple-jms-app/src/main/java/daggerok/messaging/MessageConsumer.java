package daggerok.messaging;

import daggerok.domain.Message;
import daggerok.domain.MessageRestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageConsumer {

  final MessageRestRepository messageRepository;

  @Transactional
  @JmsListener(destination = "jms.topic.message")
  public void receive(final Message message) {
    val result = messageRepository.save(message);
    log.info("consumed: {}", result);
  }
}
