package daggerok.domain.audit;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;
import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditEntity implements Serializable {

  private static final long serialVersionUID = 3944321929850899149L;

  @Id
  @GeneratedValue(strategy = IDENTITY)
  Long id;

  @CreatedDate
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
  @DateTimeFormat(iso = DATE_TIME)
  LocalDateTime createdDate;

  @LastModifiedDate
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
  @DateTimeFormat(iso = DATE_TIME)
  LocalDateTime modifiedDate;

  @CreatedBy
  String createdBy;

  @LastModifiedBy
  String lastModifiedBy;
}
