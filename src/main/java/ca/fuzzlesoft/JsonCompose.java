package ca.fuzzlesoft;

import java.util.*;

/**
 * Composes JSON, converting from {@link List}s and {@link Map}s of primitives. Is thread safe.
 *
 * @author mitch
 * @since 30/12/15
 */
public class JsonCompose {

    private JsonCompose() {}

    public static String compose(boolean data) {
        if (data) {
            return "true";
        } else {
            return "false";
        }
    }

    public static String compose(int data) {
        return Integer.toString(data);
    }

    public static String compose(double data) {
        return Double.toString(data);
    }

    public static String compose(String data) {
        if (data == null) {
            return "null";
        }

        return "\"" + data + "\"";
    }

    public static String compose(Map<String, Object> data) {
        if (data == null) {
            return "null";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("{");

        boolean first = true;
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (first) {
                first = false;
            } else {
                builder.append(",");
            }

            builder.append("\"").append(entry.getKey()).append("\":");
            Object child = entry.getValue();
            if (child == null) {
                builder.append("null");
            } else if (child instanceof String) {
                builder.append(compose((String) child));
            } else if (child instanceof Map) {
                builder.append(compose((Map<String, Object>) child));
            } else if (child instanceof Collection) {
                builder.append(compose((Collection) child));
            } else if (child instanceof Integer) {
                builder.append(compose((Integer) child));
            } else if (child instanceof Double) {
                builder.append(compose((Double) child));
            } else if (child instanceof Boolean) {
                builder.append(compose((Boolean) child));
            } else {
                throw new JsonComposeException("Unexpected child of type \"" + child.getClass() + "\"");
            }
        }
        builder.append("}");
        return builder.toString();
    }

    public static String compose(Collection data) {
        if (data == null) {
            return "null";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("[");

        boolean first = true;
        for (Object child : data) {
            if (first) {
                first = false;
            } else {
                builder.append(",");
            }

            if (child == null) {
                builder.append("null");
            } else if (child instanceof String) {
                builder.append(compose((String) child));
            } else if (child instanceof Map) {
                builder.append(compose((Map<String, Object>) child));
            } else if (child instanceof Collection) {
                builder.append(compose((Collection) child));
            } else if (child instanceof Integer) {
                builder.append(compose((Integer) child));
            } else if (child instanceof Double) {
                builder.append(compose((Double) child));
            } else if (child instanceof Boolean) {
                builder.append(compose((Boolean) child));
            } else {
                throw new JsonComposeException("Unexpected child of type \"" + child.getClass() + "\"");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}