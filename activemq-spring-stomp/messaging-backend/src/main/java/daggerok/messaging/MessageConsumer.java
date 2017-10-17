package daggerok.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import daggerok.domain.Message;
import daggerok.domain.MessageRestRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageConsumer {

  final ObjectMapper objectMapper;
  final MessageRestRepository messageRepository;
  final SimpMessagingTemplate brokerMessagingTemplate;

  @SneakyThrows
  @Transactional
  @JmsListener(destination = "jms.topic.messages")
  public void dot(final Message message) {
    val result = messageRepository.save(message);
    log.debug("saving: {}", result);
    brokerMessagingTemplate.convertAndSend("/topic/messages", objectMapper.writeValueAsString(result));
  }
}
