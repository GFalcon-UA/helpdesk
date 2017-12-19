package com.javamog.potapov.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javamog.potapov.utils.State;
import com.javamog.potapov.utils.Urgency;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TICKET")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TICKET_ID")
    private int id;

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
    private State state;

    @Column(name = "urgency_id")
    @Enumerated(EnumType.STRING)
    private Urgency urgency;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ManyToOne
    @JoinColumn(name = "approver_id")
    private User approver;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "historyTicket", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<History> ticketHistory;

    @OneToMany(mappedBy = "attachmentTicket", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Attachment> ticketAttachments;

    @OneToMany(mappedBy = "commentTicket", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> ticketComments;

    @OneToMany(mappedBy = "feedbackTicket", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Feedback> ticketFeedback;

    public int getId() {
        return id;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
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

    public void setTicketHistory(List<History> ticketHistory) {
        this.ticketHistory = ticketHistory;
    }

    public List<Attachment> getTicketAttachments() {
        return ticketAttachments;
    }

    public void setTicketAttachments(List<Attachment> ticketAttachments) {
        this.ticketAttachments = ticketAttachments;
    }

    public List<Comment> getTicketComments() {
        return ticketComments;
    }

    public void setTicketComments(List<Comment> ticketComments) {
        this.ticketComments = ticketComments;
    }

    public List<Feedback> getTicketFeedback() {
        return ticketFeedback;
    }

    public void setTicketFeedback(List<Feedback> ticketFeedback) {
        this.ticketFeedback = ticketFeedback;
    }
}
