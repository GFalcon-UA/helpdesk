package com.javamog.potapov.dto.models;

import com.javamog.potapov.domain.Category;
import com.javamog.potapov.domain.Feedback;
import com.javamog.potapov.domain.User;
import com.javamog.potapov.domain.enums.State;
import com.javamog.potapov.domain.enums.Urgency;
import lombok.Data;

import java.util.Date;

@Data
public class TicketDTO {
    private Long id;
    private String name;
    private String description;
    private Date createdOn;
    private Date desiredDate;
    private State state;
    private Urgency urgency;
    private User owner;
    private User assignee;
    private User approver;
    private Category category;
    private Feedback feedback;
}
