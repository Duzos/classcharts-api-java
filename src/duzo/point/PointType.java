package duzo.point;

import java.io.Serializable;

public enum PointType {
    POSITIVE("positive"),
    NEGATIVE("negative");

    String polarity;
    PointType(String polarity) {
        this.polarity = polarity;
    }
    public static PointType fromPolarity(String polarity) {
        for (PointType type : PointType.values()) {
            if (type.polarity.equals(polarity)) return type;
        }
        return null;
    }
}
