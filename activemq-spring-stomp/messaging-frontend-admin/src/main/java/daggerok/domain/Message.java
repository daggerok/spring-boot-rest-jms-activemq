package daggerok.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Message implements Serializable {

  private static final long serialVersionUID = 3142755581211763735L;

  @NotBlank(message = "Message body may not be null or empty")
  String body;
}
