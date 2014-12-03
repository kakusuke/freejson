package com.github.kakusuke;

import com.github.kakusuke.type.JsonObject;
import com.github.kakusuke.type.JsonValue;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created by kakusuke on 2014/11/17.
 */
public class FreeJson {
    public static String json() {
        return JsonValue.valueOf(null).toString();
    }
    public static String json(String value) {
        return JsonValue.valueOf(value).toString();
    }
    public static String json(Boolean value) {
        return JsonValue.valueOf(value).toString();
    }
    public static String json(Number value) {
        return JsonValue.valueOf(value).toString();
    }
    public static <T> String json(Collection<T> value) {
        return JsonValue.valueOf(value).toString();
    }
    public static <T> String json(Stream<T> value) {
        return JsonValue.valueOf(value).toString();
    }
    public static String json(Consumer<JsonObject> value) {
        return JsonValue.valueOf(value).toString();
    }
    public static <T> String json(Collection<T> value, BiConsumer<JsonObject, T> biConsumer) {
        return JsonValue.valueOf(value, biConsumer).toString();
    }
    public static <T> String json(Stream<T> value, BiConsumer<JsonObject, T> biConsumer) {
        return JsonValue.valueOf(value, biConsumer).toString();
    }

 }
