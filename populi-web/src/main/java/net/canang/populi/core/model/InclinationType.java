package net.canang.populi.core.model;

/**
 * @author rafizan.baharum
 * @since 7/7/13
 */
public enum InclinationType {

    BLUE(0), // 0
    BROWN(1),       // 1
    RED(2);   // 2

    private int code;

    InclinationType(int code) {
        this.code = code;
    }

    @org.codehaus.jackson.annotate.JsonValue
    public int code() {
        return code;
    }

    @org.codehaus.jackson.annotate.JsonCreator
    public static InclinationType fromValue(int typeCode) {
        return InclinationType.values()[typeCode];
    }
}
