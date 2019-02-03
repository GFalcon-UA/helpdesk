/*
 *  MIT License
 * -----------
 *
 * Copyright (c) 2016-2019 Oleksii V. KHALIKOV, PE (gfalcon.com.ua)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ua.com.gfalcon.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gfalcon.helpdesk.domain.enums.State;
import ua.com.gfalcon.helpdesk.domain.enums.Urgency;
import ua.com.gfalcon.utils.repository.entity.AbstractEntity;

import javax.persistence.*;
import java.util.*;



@Entity
@Table(name = "TICKETS")
public class Ticket extends AbstractEntity {

    @JsonProperty("sName")
    private String name;

    @JsonProperty("sDescription")
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonProperty("dCreatedOn")
    private Date createdOn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonProperty("dDesiredDate")
    private Date desiredDate;

    @Enumerated(EnumType.STRING)
    @JsonProperty("sState")
    private State state;

    @Enumerated(EnumType.STRING)
    @JsonProperty("sUrgency")
    private Urgency urgency;

    @ManyToOne
    @JsonManagedReference
    @JsonProperty("oOwner")
    private User owner;

    @ManyToOne
    @JsonManagedReference
    @JsonProperty("oAssignee")
    private User assignee;

    @ManyToOne
    @JsonManagedReference
    @JsonProperty("oApprover")
    private User approver;

    @ManyToOne
    @JsonManagedReference
    @JsonProperty("oCategory")
    private Category category;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonProperty("aoHistory")
    private Set<History> histories = new TreeSet<>((o1, o2) -> o1.getDate().compareTo(o2.getDate()));

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonProperty("afAttachments")
    private Set<Attachment> attachments = new HashSet<>();

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonProperty("aoComments")
    private Set<Comment> comments = new TreeSet<>((o1, o2) -> o1.getDate().compareTo(o2.getDate()));

    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonProperty("oFeedback")
    private Feedback feedback;


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


    protected void setOwner(User owner) {
        this.owner = owner;
    }


    public User getAssignee() {
        return assignee;
    }


    protected void setAssignee(User assignee) {
        this.assignee = assignee;
    }


    public User getApprover() {
        return approver;
    }


    protected void setApprover(User approver) {
        this.approver = approver;
    }


    public Category getCategory() {
        return category;
    }


    protected void setCategory(Category category) {
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


    public Feedback getFeedback() {
        return feedback;
    }


    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
        feedback.setTicket(this);
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdOn=" + createdOn +
                ", desiredDate=" + desiredDate +
                ", state=" + state +
                ", urgency=" + urgency +
                ", owner=" + owner.getId() +
                ", assignee=" + assignee.getId() +
                ", approver=" + approver.getId() +
                ", category=" + category.getId() +
                ", feedback=" + feedback.getId() +
                "} " + super.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Ticket))
            return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(getName(), ticket.getName()) &&
                Objects.equals(getDescription(), ticket.getDescription()) &&
                Objects.equals(getCreatedOn(), ticket.getCreatedOn()) &&
                Objects.equals(getDesiredDate(), ticket.getDesiredDate()) &&
                getState() == ticket.getState() &&
                getUrgency() == ticket.getUrgency() &&
                Objects.equals(getOwner(), ticket.getOwner()) &&
                Objects.equals(getAssignee(), ticket.getAssignee()) &&
                Objects.equals(getApprover(), ticket.getApprover()) &&
                Objects.equals(getCategory(), ticket.getCategory()) &&
                Objects.equals(getFeedback(), ticket.getFeedback());
    }


    @Override
    public int hashCode() {

        return Objects
                .hash(getName(), getDescription(), getCreatedOn(), getDesiredDate(), getState(), getUrgency(),
                        getOwner(),
                        getAssignee(), getApprover(), getCategory(), getFeedback());
    }

}
