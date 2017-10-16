package daggerok.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mq")
public class MessageQueueProperties {

  /**
   * ActiveMQ user
   */
  String user;

  /**
   * ActiveMQ password
   */
  String password;

  /**
   * ActiveMQ URL
   */
  String url;

  /**
   * ActiveMQ server
   */
  Server server;

  /**
   * ActiveMQ STOMP
   */
  Stomp stomp;

  /* maps */

  @Data
  public static class Server {

    /**
     * ActiveMQ Server port
     */
    Integer port;

    /**
     * ActiveMQ Server host
     */
    String host;
  }

  @Data
  public static class Stomp {

    /**
     * ActiveMQ STOMP host
     */
    String host;

    /**
     * ActiveMQ STOMP port
     */
    Port port;

    /**
     * ActiveMQ STOMP URL
     */
    Url url;

    String endpoint;
    String sendPath;
    String subscription;

    @Data
    public static class Port {

      /**
       * ActiveMQ STOMP TCP port
       */
      Integer tcp;

      /**
       * ActiveMQ STOMP WebSocket port
       */
      Integer ws;
    }

    @Data
    public static class Url {

      /**
       * ActiveMQ STOMP TCP URL
       */
      String tcp;

      /**
       * ActiveMQ STOMP WebSocket URL
       */
      String ws;
    }
  }
}
