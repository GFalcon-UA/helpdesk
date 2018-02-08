package com.javamog.potapov.dto.models;

import com.javamog.potapov.domain.History;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String role;
    private String email;
    private String addres;
    private List<TicketDTO> ownTickets;
    private List<TicketDTO> assignTickets;
    private List<TicketDTO> approveTickets;
    private List<History> histories;
    private List<CommentDTO> comments;
    private List<FeedbackDTO> feedbacks;

}
