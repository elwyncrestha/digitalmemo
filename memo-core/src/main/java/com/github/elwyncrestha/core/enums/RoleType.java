package com.github.elwyncrestha.core.enums;

/**
 * @author Elvin Shrestha on 6/20/2020
 */
public enum RoleType {

    MAKER("Maker"), APPROVAL("Approval");

    private final String value;

    RoleType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
