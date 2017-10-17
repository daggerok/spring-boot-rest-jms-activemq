package daggerok.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "db")
public class DatabaseProperties {

  /**
   * Database name.
   */
  String name;

  /**
   * Database user username.
   */
  String user;

  /**
   * Database user password.
   */
  String pass;

  /**
   * Database server.
   */
  @Data
  public static class Server {

    /**
     * Database server hostname.
     */
    Integer port;

    /**
     * Database server listening port.
     */
    String host;
  }
}
