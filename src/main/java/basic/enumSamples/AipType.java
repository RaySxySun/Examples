package basic.enumSamples;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum AipType {
    // the the enum value could be negative number.
    UNKNOWN(-1),
    NONE(0),
    MOD(1),
    NO_MOD(2);

    private static final Map<Integer, AipType> VALUE_TO_ENUM_MAP;
    private final int value;

    static {
        VALUE_TO_ENUM_MAP = new HashMap<>();
        for (AipType type : EnumSet.allOf(AipType.class)) {
            VALUE_TO_ENUM_MAP.put(type.value, type);
        }
    }

    private AipType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AipType forValue(int value) {
        return VALUE_TO_ENUM_MAP.get(value);
    }

    public static void main(String[] args) {
        System.out.println(AipType.UNKNOWN.getValue());
    }
}