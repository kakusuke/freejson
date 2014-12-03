package com.github.kakusuke;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created by kakusuke on 2014/11/30.
 */
interface JsonValue {
    public static JsonValue valueOf(Object value) {
        if (value == null)               return JsonPrimitive.nullValue;
        if (value instanceof JsonValue)  return (JsonValue) value;
        if (value instanceof Boolean)    return new JsonPrimitive<Boolean>((Boolean) value);
        if (value instanceof Number)     return new JsonPrimitive<Number>((Number) value);
        if (value instanceof String)     return new JsonPrimitive<String>((String) value);
        if (value instanceof Collection) return new JsonArray<>((Collection<?>) value);
        if (value instanceof Stream)     return new JsonArray<>((Stream<?>) value);
        if (value instanceof Consumer)   return new JsonObject((Consumer<JsonObject>) value);
        return JsonPrimitive.nullValue;
    }
    public static <E> JsonValue valueOf(Collection value, BiConsumer<JsonObject, E> biConsumer) {
        if (value == null) return JsonPrimitive.nullValue;
        return valueOf(value.stream(), biConsumer);
    }
    public static <E> JsonValue valueOf(Stream<E> value, BiConsumer<JsonObject, E> biConsumer) {
        if (value == null) return JsonPrimitive.nullValue;
        return JsonValue.valueOf(value.map((v) -> new JsonObject(biConsumer, v)));
    }
}
