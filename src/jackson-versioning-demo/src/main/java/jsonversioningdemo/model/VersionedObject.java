package jsonversioningdemo.model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.NonNull;
import lombok.Value;

import java.io.IOException;
import java.util.Iterator;

/**
 * Domain object for versioned objects.
 */
@Value
public class VersionedObject<T extends VersionAware> {

  private static final ObjectMapper MAPPER = new ObjectMapper()
      .configure(SerializationFeature.INDENT_OUTPUT, true)
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  private static final int INITIAL_VERSION = 1;

  private static final String VERSION_FIELD = "version";

  int version;
  @NonNull T payload;
  @NonNull JsonNode rawTree;

  /**
   * Creates new versioned object off of versioned payload.
   *
   * @param payload Payload
   * @param <T> Payload type
   * @return Versioned object instance
   */
  @NonNull public static <T extends VersionAware> VersionedObject<T> of(@NonNull T payload) {
    return new VersionedObject<>(payload.getVersion(), payload, MAPPER.createObjectNode());
  }

  /**
   * Attaches new payload to the object. Suitable for update scenarios.
   *
   * @param payload Payload
   * @param <Tv2> New payload type
   * @return Versioned object instance
   */
  @NonNull public <Tv2 extends VersionAware> VersionedObject<Tv2> join(@NonNull Tv2 payload) {
    return new VersionedObject<>(version, payload, rawTree);
  }

  /**
   * Reads object from JSON.
   */
  @NonNull public static <T extends VersionAware> VersionedObject<T> fromJson(
      @NonNull String content,
      @NonNull Class<T> payloadClass,
      int minVersion
  ) throws IOException {
    return fromJson(content, payloadClass, minVersion, Integer.MAX_VALUE);
  }

  /**
   * Reads object from JSON.
   */
  @NonNull public static <T extends VersionAware> VersionedObject<T> fromJson(
      @NonNull String content,
      @NonNull Class<T> payloadClass,
      int minVersion,
      int maxVersion
  ) throws IOException {
    final JsonNode raw = MAPPER.readTree(content);
    final JsonNode versionNode = raw.get(VERSION_FIELD);
    final int version;
    if (versionNode.isNull()) {
      version = INITIAL_VERSION;
    } else {
      if (!versionNode.isIntegralNumber()) {
        throw new IOException("Version field expected to be an int, got: " + versionNode);
      }
      version = versionNode.asInt();
    }

    if (minVersion > version || maxVersion < version) {
      throw new IOException(String.format("Version mismatch, expected to be in (%d, %d) got %d in %s",
          minVersion, maxVersion, version, raw));
    }

    // recover actual payload
    final T payload = MAPPER.treeToValue(raw, payloadClass);

    return new VersionedObject<>(version, payload, raw);
  }

  /**
   * Converts versioned object to JSON.
   */
  @NonNull public String toJson() throws IOException {
    // set payload
    final ObjectNode result = MAPPER.valueToTree(payload);
    // set non-overridden fields
    merge(result, rawTree);
    // set actual version
    result.set(VERSION_FIELD, new IntNode(payload.getVersion()));

    return MAPPER.writeValueAsString(result);
  }

  private static void merge(JsonNode to, JsonNode from) {
    if (!to.isObject() || !from.isObject()) {
      return;
    }

    final ObjectNode toObj = (ObjectNode) to;
    for (Iterator<String> it = from.fieldNames(); it.hasNext();) {
      final String fieldName = it.next();
      final JsonNode fieldValue = toObj.get(fieldName);
      final JsonNode prevValue = from.get(fieldName);
      if (fieldValue != null) {
        merge(fieldValue, prevValue);
        continue;
      }
      toObj.set(fieldName, prevValue);
    }
  }
}
