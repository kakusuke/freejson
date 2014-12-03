package com.github.kakusuke.type;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by kakusuke on 2014/11/18.
 */
public class JsonObject implements JsonValue {
    public JsonObject(Consumer<JsonObject> consumer) {
        consumer.accept(this);
    }

    public <E> JsonObject(BiConsumer<JsonObject, E> biConsumer, E v) {
        biConsumer.accept(this, v);
    }

    public JsonObject entry(String key, JsonValue value) {
        return put(key, value);
    }
    public JsonObject entry(String key, String value) {
        return put(key, value);
    }
    public JsonObject entry(String key, Boolean value) {
        return put(key, value);
    }
    public JsonObject entry(String key, Number value) {
        return put(key, value);
    }
    public JsonObject entry(String key, Consumer<JsonObject> value) {
        return put(key, value);
    }
    public <E> JsonObject entry(String key, Collection<E> value) {
        return put(key, value);
    }
    public <E> JsonObject entry(String key, Stream<E> value) {
        return put(key, value);
    }
    public <E> JsonObject entry(String key, Collection<E> value, BiConsumer<JsonObject, E> biConsumer) {
        return put(key, JsonValue.valueOf(value, biConsumer));
    }
    public <E> JsonObject entry(String key, Stream<E> value, BiConsumer<JsonObject, E> biConsumer) {
        return put(key, JsonValue.valueOf(value, biConsumer));
    }


    @Override
    public String toString() {
        return "{" + entries.entrySet().stream()
                    .map((e)-> JsonPrimitive.stringify(e.getKey()) + ":" + e.getValue())
                    .collect(Collectors.joining(","))
                + "}";
   }

    private Map<String, JsonValue> entries = new LinkedHashMap<>();
    private JsonObject put(String key, Object value) {
        entries.put(key, JsonValue.valueOf(value));
        return this;
    }
}
