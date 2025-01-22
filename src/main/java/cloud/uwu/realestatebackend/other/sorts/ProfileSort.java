package cloud.uwu.realestatebackend.other.sorts;

import lombok.Getter;

@Getter
public enum ProfileSort {
    COUNTRY("country"),
    STATE("state"),
    CITY("city");

    private final String field;

    ProfileSort(String field) {
        this.field = field;
    }

    public static boolean isValid(String field) {
        for (ProfileSort sortField : values()) {
            if (sortField.getField().equalsIgnoreCase(field)) {
                return true;
            }
        }
        return false;
    }
}
