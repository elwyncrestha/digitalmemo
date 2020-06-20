package com.github.elwyncrestha.core.enums;

/**
 * @author Elvin Shrestha on 6/20/2020
 */
public enum RoleAccess {

    OWN("Own"), SPECIFIC("Specific"), ALL("All");

    private final String value;

    RoleAccess(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
