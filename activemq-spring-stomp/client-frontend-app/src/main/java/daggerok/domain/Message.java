package daggerok.domain;

import daggerok.domain.audit.AbstractAuditEntity;
import daggerok.domain.json.JsonDocument;
import daggerok.domain.json.JsonDocumentType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Entity
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TypeDef(name = "JsonDocumentType", typeClass = JsonDocumentType.class)
public class Message extends AbstractAuditEntity implements Serializable {

  private static final long serialVersionUID = -9142755581211763735L;

  @NotBlank(message = "Message body may not be null or empty")
  @Column(nullable = false)
  String body;

  @Type(type = "JsonDocumentType")
  JsonDocument document;
}
