package ca.fuzzlesoft;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author mitch
 * @since 30/12/15
 */
public class JsonComposeTest {

    @Test
    public void shouldComposeIntegers() {
        Assert.assertEquals("123", JsonCompose.compose(123));
    }

    @Test
    public void shouldComposeLongs() {
        Assert.assertEquals("1", JsonCompose.compose(1L));
        Assert.assertEquals("[1]", JsonCompose.compose(Collections.singletonList(1L)));
    }

    @Test
    public void shouldComposeComplexNumbers() {
        Assert.assertEquals("-2.2", JsonCompose.compose(-2.2));
    }

    @Test
    public void shouldComposeString() {
        Assert.assertEquals("\"test\"", JsonCompose.compose("test"));
    }

    @Test
    public void shouldComposeNull() {
        Assert.assertEquals("null", JsonCompose.compose((String) null));
        Assert.assertEquals("null", JsonCompose.compose((Map<String, Object>) null));
        Assert.assertEquals("null", JsonCompose.compose((List) null));
    }

    @Test
    public void shouldComposeBoolean() {
        Assert.assertEquals("false", JsonCompose.compose(false));
        Assert.assertEquals("true", JsonCompose.compose(true));
    }

    @Test
    public void shouldComposeObject() {
        Map<String, Object> data = new MapBuilder()
                .add("key", 1)
                .build();
        Assert.assertEquals("{\"key\":1}", JsonCompose.compose(data));
    }

    @Test
    public void shouldComposeList() {
        List data = Arrays.asList("bork", 4, false, null);
        Assert.assertEquals("[\"bork\",4,false,null]", JsonCompose.compose(data));
    }

    @Test
    public void shouldComposeNestedObject() {
        Map<String, Object> data = new MapBuilder()
                .add("nested", new MapBuilder().add("value", 1).build())
                .build();
        Assert.assertEquals("{\"nested\":{\"value\":1}}", JsonCompose.compose(data));
    }

    @Test(expected = JsonComposeException.class)
    public void shouldThrowExceptionIfValueNotPrimitive() {
        Map<String, Object> data = new MapBuilder()
                .add("prank", new Date())
                .build();
        JsonCompose.compose(data);
    }

    @Test
    public void shouldComposeListInObject() {
        Map<String, Object> data = new MapBuilder()
                .add("list", Arrays.asList(1, 2, 3))
                .build();
        Assert.assertEquals("{\"list\":[1,2,3]}", JsonCompose.compose(data));
    }

    @Test
    public void integrationTest() {
        Map<String, Object> data = new MapBuilder()
                .add("list", Arrays.asList(
                        new MapBuilder().add("key", null).build(),
                        "secondItem"
                ))
                .add("nested", new MapBuilder().add("false", false).add("true", true).build())
                .add("null", null)
                .add("string", "ayy")
                .add("true", true)
                .build();
        Assert.assertEquals("{\"list\":[{\"key\":null},\"secondItem\"],\"nested\":{\"false\":false,\"true\":true},\"null\":null,\"string\":\"ayy\",\"true\":true}", JsonCompose.compose(data));
    }

    private static class MapBuilder {
        private final Map<String, Object> map = new LinkedHashMap<>();

        MapBuilder add(String key, Object value) {
            map.put(key, value);
            return this;
        }

        Map<String, Object> build() {
            return map;
        }
    }
}