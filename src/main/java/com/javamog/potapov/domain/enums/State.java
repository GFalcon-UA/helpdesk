package com.javamog.potapov.domain.enums;

public enum State {
    DRAFT("Create", "Draft"),
    NEW("Submit", "New"),
    APPROVED("Approve", "Approved"),
    DECLINED("Decline", "Declined"),
    CANCELED("Cancel", "Cancelled"),
    IN_PROGRESS("Assign to Me", "In Progress"),
    DONE("Done", "Done");

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