package com.javamog.potapov.dto.models;

import lombok.Data;

@Data
public class FeedbackDTO {
    private Long id;
    private Integer rate;
    private String date;
    private String text;
    private UserDTO user;
    private TicketDTO ticket;
}
