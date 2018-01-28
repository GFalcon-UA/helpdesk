package com.javamog.potapov.model.ticket;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum TicketCategory {

    APPLICATION_SERVICES("Application & Services"),BENEFITS_PAPER_WORK("Benefits & Paper Work"),
    HARDWARE_SOFTWARE("Hardware & Software"), PEOPLE_MANAGEMENT("People Management"),SECURITY_ACCESS("Security & Access"),
    WORKPLACES_FACILITIES("Workplaces & Facilities");

    private String description;

    TicketCategory(String s) {
        this.description = s;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    public static TicketCategory getByDescription(String description){
      return Arrays.stream(TicketCategory.values()).filter(c-> c.getDescription().equals(description)).findFirst().get();
    }
}
