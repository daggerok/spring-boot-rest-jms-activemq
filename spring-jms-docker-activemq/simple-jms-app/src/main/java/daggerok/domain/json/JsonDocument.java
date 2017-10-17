package daggerok.domain.json;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class JsonDocument implements Serializable {

  private static final long serialVersionUID = -7836145617467207275L;

  Long longProp;
  String stringProp;
  Map<String, String> mapProps = new HashMap<>();
}
