package com.javamog.potapov.dto.models;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private String text;
    private String date;
    private UserDTO user;
    private TicketDTO ticket;
}
