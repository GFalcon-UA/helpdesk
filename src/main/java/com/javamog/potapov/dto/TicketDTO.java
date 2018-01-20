package com.javamog.potapov.dto;

import com.javamog.potapov.model.Feedback;
import com.javamog.potapov.model.history.History;
import com.javamog.potapov.model.ticket.*;
import com.javamog.potapov.model.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketDTO {
    private Long id;

    private String name;

    private String description;

    private Date createdOn;

    private Date desiredDate;

    private TicketStatus status;

    private TicketUrgency urgency;

    private UserDTO owner;

    private UserDTO assignee;

    private UserDTO approver;

    private TicketCategory category;

    private List<History> ticketHistory = new ArrayList<>();

    private List<Attachment> ticketAttachments = new ArrayList<>();

    private List<Comment> ticketComments = new ArrayList<>();

    private List<Feedback> ticketFeedback = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getDesiredDate() {
        return desiredDate;
    }

    public void setDesiredDate(Date date) {
        this.desiredDate = date;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public TicketUrgency getUrgency() {
        return urgency;
    }

    public void setUrgency(TicketUrgency urgency) {
        this.urgency = urgency;
    }

    public TicketCategory getCategory() {
        return category;
    }

    public void setCategory(TicketCategory category) {
        this.category = category;
    }

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    public UserDTO getAssignee() {
        return assignee;
    }

    public void setAssignee(UserDTO assignee) {
        this.assignee = assignee;
    }

    public UserDTO getApprover() {
        return approver;
    }

    public void setApprover(UserDTO approver) {
        this.approver = approver;
    }

    public List<History> getTicketHistory() {
        return ticketHistory;
    }

    private void setTicketHistory(List<History> ticketHistory) {
        this.ticketHistory = ticketHistory;
    }

    public List<Attachment> getTicketAttachments() {
        return ticketAttachments;
    }

    private void setTicketAttachments(List<Attachment> ticketAttachments) {
        this.ticketAttachments = ticketAttachments;
    }

    public List<Comment> getTicketComments() {
        return ticketComments;
    }

    private void setTicketComments(List<Comment> ticketComments) {
        this.ticketComments = ticketComments;
    }

    public List<Feedback> getTicketFeedback() {
        return ticketFeedback;
    }

    private void setTicketFeedback(List<Feedback> ticketFeedback) {
        this.ticketFeedback = ticketFeedback;
    }

    public void addHistoryRecord(History history) {
        getTicketHistory().add(history);
    }

    public void addComment(Comment comment) {
        getTicketComments().add(comment);
    }

    public void addFeedback(Feedback feedback) {
        getTicketFeedback().add(feedback);
    }

    public void addAttachment(Attachment attachment) {
        getTicketAttachments().add(attachment);
    }

    public void removeFromAttachments(Attachment attachment) {
        getTicketAttachments().remove(attachment);
    }
}
