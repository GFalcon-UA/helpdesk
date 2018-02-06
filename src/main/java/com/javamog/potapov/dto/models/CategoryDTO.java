package com.javamog.potapov.dto.models;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private List<TicketDTO> tickets;
}
