package com.javamog.potapov.dto.models;

import lombok.Data;

@Data
public class HistoryDTO {
    private Long id;
    private String date;
    private String action;
    private String description;
    private UserDTO user;
    private TicketDTO ticket;
}
