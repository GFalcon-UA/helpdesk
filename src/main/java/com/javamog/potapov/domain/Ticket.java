package com.javamog.potapov.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javamog.potapov.domain.enums.State;
import com.javamog.potapov.domain.enums.Urgency;
import com.javamog.potapov.parent.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "TICKET")
public class Ticket extends AbstractEntity {

//    @Column(name = "name")
    private String name;

//    @Column(name = "description")
    private String description;

//    @Column(name = "created_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date createdOn;

//    @Column(name = "desired_resolution_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date desiredDate;

//    @Column(name = "state_id")
    @Enumerated(EnumType.STRING)
    private State state;

//    @Column(name = "urgency_id")
    @Enumerated(EnumType.STRING)
    private Urgency urgency;

//    @ManyToOne
//    @JoinColumn(name = "owner_id")
    @ManyToOne
//    @JsonIgnore
    private User owner;

//    @ManyToOne
//    @JoinColumn(name = "assignee_id")
    @ManyToOne
//    @JsonIgnore
    private User assignee;

//    @ManyToOne
//    @JoinColumn(name = "approver_id")
    @ManyToOne
//    @JsonIgnore
    private User approver;

    //@ManyToOne
//    @JoinColumn(name = "category_id")
    @ManyToOne
//    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnore
    private Set<History> histories = new TreeSet<>((o1, o2) -> o1.getDate().compareTo(o2.getDate()));

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnore
    private Set<Attachment> attachments = new HashSet<>();

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnore
    private Set<Comment> comments = new TreeSet<>((o1, o2) -> o1.getDate().compareTo(o2.getDate()));

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnore
    private Set<Feedback> feedbacks = new TreeSet<>((o1, o2) -> o1.getDate().compareTo(o2.getDate()));

    public Ticket() {
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

    public void setDesiredDate(Date desiredDate) {
        this.desiredDate = desiredDate;
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

    public User getOwner() {
        return owner;
    }

    @Deprecated
    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getAssignee() {
        return assignee;
    }

    @Deprecated
    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public User getApprover() {
        return approver;
    }

    @Deprecated
    public void setApprover(User approver) {
        this.approver = approver;
    }

    public Category getCategory() {
        return category;
    }

    @Deprecated
    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<History> getHistories() {
        return histories;
    }

    public void setHistories(Set<History> histories) {
        this.histories = histories;
    }

    public void addHistories(History history) {
        this.histories.add(history);
        history.setTicket(this);
    }

    public void removeHistories(History history) {
        this.histories.remove(history);
        history.setTicket(null);
    }

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void addAttachments(Attachment attachment) {
        this.attachments.add(attachment);
        attachment.setTicket(this);
    }

    public void removeAttachments(Attachment attachment) {
        this.attachments.remove(attachment);
        attachment.setTicket(null);
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setTicket(this);
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setTicket(null);
    }

    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public void addFeedbacks(Feedback feedback) {
        this.feedbacks.add(feedback);
        feedback.setTicket(this);
    }

    public void removeFeedbacks(Feedback feedback) {
        this.feedbacks.remove(feedback);
        feedback.setTicket(null);
    }


}
