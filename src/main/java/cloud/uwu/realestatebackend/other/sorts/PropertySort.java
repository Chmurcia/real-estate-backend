package cloud.uwu.realestatebackend.other.sorts;

import lombok.Getter;

@Getter
public enum PropertySort {
    PRICE("price"),
    CREATED_AT("created_at"),
    VISITS("visits"),
    LIKES("likes");

    private final String field;

    PropertySort(String field) {
        this.field = field;
    }

    public static boolean isValid(String field) {
        for (PropertySort sortField : values()) {
            if (sortField.getField().equalsIgnoreCase(field)) {
                return true;
            }
        }
        return false;
    }
}
