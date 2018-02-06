package com.javamog.potapov.dto.util;

import com.javamog.potapov.domain.*;
import com.javamog.potapov.dto.models.*;
import org.modelmapper.ModelMapper;

public class DTOConverter {

    private static ModelMapper modelMapper = new ModelMapper();
    public static Category convert(CategoryDTO categoryDTO){
        Category category = modelMapper.map(categoryDTO, Category.class);

        return category;
    }

    public static Comment convert(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class);

        comment.setDate(DateConverter.convert(commentDTO.getDate()));

        return comment;
    }

    public static Feedback convert(FeedbackDTO feedbackDTO) {
        Feedback feedback = modelMapper.map(feedbackDTO, Feedback.class);

        feedback.setDate(DateConverter.convert(feedbackDTO.getDate()));

        return feedback;
    }

    public static History convert(HistoryDTO historyDTO) {
        History history = modelMapper.map(historyDTO, History.class);

        history.setDate(DateConverter.convert(historyDTO.getDate()));

        return history;
    }

    public static Ticket convert(TicketDTO ticketDTO) {
        Ticket ticket = modelMapper.map(ticketDTO, Ticket.class);

        ticket.setCreatedOn(DateConverter.convert(ticketDTO.getCreatedOn()));
        ticket.setDesiredDate(DateConverter.convert(ticketDTO.getDesiredDate()));

        return ticket;
    }

    public static User convert(UserDTO userDTO){
        User user = modelMapper.map(userDTO, User.class);

        return user;
    }

    public static CategoryDTO convert(Category category){
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

        return categoryDTO;
    }

    public static CommentDTO convert(Comment comment){
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);

        commentDTO.setDate(DateConverter.convert(comment.getDate()));

        return commentDTO;
    }

    public static FeedbackDTO convert(Feedback feedback){
        FeedbackDTO feedbackDTO = modelMapper.map(feedback, FeedbackDTO.class);

        feedbackDTO.setDate(DateConverter.convert(feedback.getDate()));

        return feedbackDTO;
    }

    public static HistoryDTO convert(History history){
        HistoryDTO historyDTO = modelMapper.map(history, HistoryDTO.class);

        historyDTO.setDate(DateConverter.convert(history.getDate()));

        return historyDTO;
    }

    public static TicketDTO convert(Ticket ticket){
        TicketDTO ticketDTO = modelMapper.map(ticket, TicketDTO.class);

        ticketDTO.setCreatedOn(DateConverter.convert(ticket.getCreatedOn()));
        ticketDTO.setDesiredDate(DateConverter.convert(ticket.getDesiredDate()));

        return ticketDTO;
    }

    public static UserDTO convert(User user){
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }


}
