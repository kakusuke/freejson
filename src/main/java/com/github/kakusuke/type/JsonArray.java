package com.github.kakusuke.type;

import java.util.Collection;
import java.util.StringJoiner;
import java.util.stream.Stream;

/**
 * Created by kakusuke on 2014/11/30.
 */
class JsonArray<T> implements JsonValue {
    private final Stream<JsonValue> list;

    public JsonArray(Stream<T> stream) {
        this.list = stream.map(JsonValue::valueOf);
    }

    public JsonArray(Collection<T> value) {
        this(value.stream());
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",");
        list.forEach((v) -> joiner.add(v.toString()));
        return "[" + joiner.toString() + "]";
    }
}
