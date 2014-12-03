package com.github.kakusuke.type;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by kakusuke on 2014/11/30.
 */
class JsonPrimitive<T> implements JsonValue {
    public static final JsonPrimitive<Object> nullValue = new JsonPrimitive<>(null);
    private static final Map<String, String> SPECIAL_CHAR_MAP = new HashMap<String, String>() {{
        put("/", "\\/");
        put("\"", "\\\"");
        put("\\", "\\\\");
        put("\f", "\\f");
        put("\n", "\\n");
        put("\r", "\\r");
        put("\t", "\\t");
        put("\b", "\\b");
    }};

    private T value;
    public JsonPrimitive(T value) {
        this.value = value;
    }

    public String toString() {
        if (value == null)            return "null";
        if (value instanceof Boolean) return value.toString();
        if (value instanceof Number)  return value.toString();
        return stringify(value.toString());
    }

    static String stringify(String value) {
        return "\""
                + Stream.of(value.split(""))
                .map((c) ->  SPECIAL_CHAR_MAP.getOrDefault(c, c))
                .collect(Collectors.joining())
                + "\"";
    }
}
