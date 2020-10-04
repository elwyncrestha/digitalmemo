package com.github.elwyncrestha.core.enums;

/**
 * @author Elvin Shrestha on 10/4/2020
 */
public enum DocStatus {
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected");

    private final String value;

    DocStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
