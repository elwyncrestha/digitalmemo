package com.github.elwyncrestha.core.enums;

/**
 * @author Elvin Shrestha on 6/20/2020
 */
public enum PatchType {
    INITIAL_ROLE_AND_USER("initial_role_and_user.sql");

    private final String value;

    PatchType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
