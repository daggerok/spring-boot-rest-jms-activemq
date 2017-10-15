package daggerok.domain.json;

import org.hibernate.dialect.PostgreSQL94Dialect;

import java.sql.Types;

public class JsonPostgresDialect extends PostgreSQL94Dialect {
  public JsonPostgresDialect() {
    this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
  }
}
