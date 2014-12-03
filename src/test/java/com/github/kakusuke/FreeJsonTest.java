package com.github.kakusuke;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

import static com.github.kakusuke.FreeJson.json;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class FreeJsonTest extends TestCase {
    @Test
    public void values() throws Exception {
        assertThat(json(), is("null"));
        assertThat(json(1), is("1"));
        assertThat(json(1.1), is("1.1"));
        assertThat(json("a"), is("\"a\""));
        assertThat(json(true), is("true"));
        assertThat(json((o)-> o.entry("foo", "bar")), is("{\"foo\":\"bar\"}"));
        assertThat(json((o) -> {
            o.entry("foo", "bar");
            o.entry("bar", "buz");
        }), is("{\"foo\":\"bar\",\"bar\":\"buz\"}"));
        assertThat(json(Arrays.asList(1, 2)), is("[1,2]"));
    }

    @Test
    public void nestedObject() throws Exception {
        assertThat(json((o)-> {
            o.entry("foo", (o2)-> {
                o2.entry("bar", "buz");
            });
        }), is("{\"foo\":{\"bar\":\"buz\"}}"));
        assertThat(json((o)-> o.entry("foo", Arrays.asList(1, 2))),
                is("{\"foo\":[1,2]}"));
    }

    @Test
    public void nestedArray() throws Exception {
        assertThat(json(Arrays.asList(Arrays.asList(1, 2))),
                is("[[1,2]]"));
        assertThat(json(Arrays.asList(1, 2), (o, v) -> o.entry("foo", v)),
                is("[{\"foo\":1},{\"foo\":2}]"));
        assertThat(json(
                Arrays.asList(1, 2), (o, v)-> {
                    o.entry("foo", Arrays.<Integer>asList(v * 2, v *3), (o2, v2)-> {
                        o2.entry("bar", v2);
                    });
                }),
                is("[{\"foo\":[{\"bar\":2},{\"bar\":3}]},{\"foo\":[{\"bar\":4},{\"bar\":6}]}]"));
    }

    @Test
    public void specialChar() throws Exception {
        assertThat(json("\\"), is("\"\\\\\""));
        assertThat(json("\""), is("\"\\\"\""));
        assertThat(json("/"), is("\"\\/\""));
        assertThat(json("\b"), is("\"\\b\""));
        assertThat(json("\f"), is("\"\\f\""));
        assertThat(json("\n"), is("\"\\n\""));
        assertThat(json("\r"), is("\"\\r\""));
        assertThat(json("\t"), is("\"\\t\""));
    }
}
