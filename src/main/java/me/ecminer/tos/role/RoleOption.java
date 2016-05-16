package me.ecminer.tos.role;

import java.util.List;

public class RoleOption {

    public static final String JAILED = "default.jailed";
    public static final String ROLE_BLOCKED = "default.role_blocked";
    public static final String KILLED = "default.killed";

    private final String identifier;
    private final Object obj;

    public RoleOption(String identifier, Object obj) {
        this.identifier = identifier;
        this.obj = obj;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Object value() {
        return obj;
    }

    public String asString() {
        return (String) obj;
    }

    public int asInt() {
        return asNumber().intValue();
    }

    public byte asByte() {
        return asNumber().byteValue();
    }

    public short asShort() {
        return asNumber().shortValue();
    }

    public long asLong() {
        return asNumber().longValue();
    }

    public float asFloat() {
        return asNumber().floatValue();
    }

    public double asDouble() {
        return asNumber().doubleValue();
    }

    public boolean asBoolean() {
        return obj instanceof Boolean && (boolean) obj;
    }

    public <T> T asType(Class<T> cls) {
        if (obj.getClass().isInstance(cls) || obj.getClass().isAssignableFrom(cls)) {
            return (T) obj;
        }
        return null;
    }

    public <T> List<T> asList(Class<T> genericType) {
        return (List<T>) obj;
    }

    public <T> void addToList(T value) {
        if (value instanceof List)
            ((List) obj).add(value);
    }

    public Number asNumber() {
        if (obj instanceof Number) {
            return (Number) obj;
        }
        return null;
    }
}
