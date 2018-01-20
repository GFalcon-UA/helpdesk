package com.javamog.potapov.model.ticket;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.javamog.potapov.model.Feedback;
import com.javamog.potapov.model.history.History;
import com.javamog.potapov.model.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TICKET")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TICKET_ID", unique = true)

    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date createdOn;

    @Column(name = "desired_resolution_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date desiredDate;

    @Column(name = "state_id")
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Column(name = "urgency_id")
    @Enumerated(EnumType.STRING)
    private TicketUrgency urgency;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ManyToOne
    @JoinColumn(name = "approver_id")
    private User approver;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private TicketCategory category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<History> ticketHistory = new ArrayList<>();;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Attachment> ticketAttachments = new ArrayList<>();;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> ticketComments = new ArrayList<>();;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
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

    public void addHistoryRecord(History history){
        getTicketHistory().add(history);
    }

    public void addComment(Comment comment){
        getTicketComments().add(comment);
    }

    public void addFeedback(Feedback feedback){
        getTicketFeedback().add(feedback);
    }

    public void addAttachment(Attachment attachment){
        getTicketAttachments().add(attachment);
    }

    public void removeFromAttachments(Attachment attachment) {
        getTicketAttachments().remove(attachment);
    }
}
