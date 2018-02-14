package com.javamog.potapov.dto.models;

import lombok.Data;

@Data
public class TicketDTO {
    private Long id;
    private Long categoryId;
    private String name;
    private String description;
    private String urgency;
    private String desiredDate;
    private String comment;
    private String state;
    private Long ownerId;
    private Long assigneeId;
    private Long approverId;
    private String createdOn;

}
