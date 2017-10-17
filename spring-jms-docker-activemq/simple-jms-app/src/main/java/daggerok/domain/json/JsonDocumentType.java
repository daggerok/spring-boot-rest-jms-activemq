package daggerok.domain.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.isNull;

public class JsonDocumentType implements UserType {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public int[] sqlTypes() {
    return new int[]{ Types.JAVA_OBJECT };
  }

  @Override
  public Class<JsonDocument> returnedClass() {
    return JsonDocument.class;
  }

  @Override
  public boolean equals(final Object x, final Object y) {
    return isNull(x) ? isNull(y) : x.equals(y);
  }

  @Override
  public int hashCode(final Object x) {
    return x.hashCode();
  }

  @Override
  @SneakyThrows
  public Object nullSafeGet(final ResultSet rs,
                            final String[] names,
                            final SessionImplementor session,
                            final Object owner) {

    val cellContent = rs.getString(names[0]);

    if (isNull(cellContent)) {
      return null;
    }

    return objectMapper.readValue(cellContent.getBytes(UTF_8), returnedClass());
  }

  @Override
  @SneakyThrows
  public void nullSafeSet(final PreparedStatement st,
                          final Object value,
                          final int index,
                          final SessionImplementor session) {
    if (isNull(value)) {
      st.setNull(index, Types.OTHER);
      return;
    }

    val stringWriter = new StringWriter();

    objectMapper.writeValue(stringWriter, value);
    stringWriter.flush();
    st.setObject(index, stringWriter.toString(), Types.OTHER);
  }

  @Override
  @SneakyThrows
  public Object deepCopy(final Object value) throws HibernateException {
    // use serialization to create a deep copy
    @Cleanup val baos = new ByteArrayOutputStream();
    @Cleanup val oos = new ObjectOutputStream(baos);
    oos.writeObject(value);
    oos.flush();

    @Cleanup val bais = new ByteArrayInputStream(baos.toByteArray());
    @Cleanup val ois = new ObjectInputStream(bais);
    return ois.readObject();
  }

  @Override
  public boolean isMutable() {
    return true;
  }

  @Override
  public Serializable disassemble(final Object value) {
    return (Serializable) deepCopy(value);
  }

  @Override
  public Object assemble(final Serializable cached, final Object owner) {
    return deepCopy(cached);
  }

  @Override
  public Object replace(final Object original, final Object target, final Object owner) {
    return deepCopy(original);
  }
}
