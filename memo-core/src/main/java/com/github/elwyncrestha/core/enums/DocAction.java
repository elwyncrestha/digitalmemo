package com.github.elwyncrestha.core.enums;

/**
 * @author Elvin Shrestha on 10/4/2020
 */
public enum DocAction {
    DRAFT("Draft"),
    FORWARD("Forward"),
    BACKWARD("Backward"),
    APPROVE("Approve"),
    REJECT("Reject");

    private final String value;

    DocAction(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
