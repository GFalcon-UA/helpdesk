package com.javamog.potapov.dto.models;

import lombok.Data;

import java.util.List;

@Data
public class TicketDTO {
    private Long id;
    private CategoryDTO category;
    private String name;
    private String description;
    private String urgency;
    private String desiredDate;
    private List<CommentDTO> comments;
    private String state;
    private UserDTO owner;
    private UserDTO assignee;
    private UserDTO approver;
    private String createdOn;

}
