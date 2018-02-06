package com.javamog.potapov.domain.enums;

public enum State {
    DRAFT("Create", "Draft"),
    NEW("Submit", "New"),
    APPROVED("Approve", "Approved"),
    DECLINED("Decline", "Declined"),
    IN_PROGRESS("Cancel", "Cancelled"),
    DONE("Assign to Me", "In Progress"),
    CANCELED("Done", "Done");

    private String action;
    private String status;

    State(String action, String status){
        this.action = action;
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return getStatus();
    }
}