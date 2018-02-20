package com.javamog.potapov.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javamog.potapov.parent.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "HISTORIES")
public class History extends AbstractEntity {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm")
    @JsonProperty("dDate")
    private Date date = new Date();

    @JsonProperty("sAction")
    private String action;

    @JsonProperty("sDescription")
    private String description;

    @JsonManagedReference
    @ManyToOne
    @JsonProperty("oUser")
    private User user;

    @JsonBackReference
    @ManyToOne
    @JsonProperty("oTicket")
    private Ticket ticket;

    public History() {
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    protected void setUser(User user) {
        this.user = user;
    }

    public Ticket getTicket() {
        return ticket;
    }

    protected void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "History{" +
                "date=" + date +
                ", action='" + action + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user.getId() +
                ", ticket=" + ticket.getId() +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof History))
            return false;
        History history = (History) o;
        return Objects.equals(getDate(), history.getDate()) &&
                Objects.equals(getAction(), history.getAction()) &&
                Objects.equals(getDescription(), history.getDescription()) &&
                Objects.equals(getUser(), history.getUser()) &&
                Objects.equals(getTicket(), history.getTicket());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getDate(), getAction(), getDescription(), getUser(), getTicket());
    }
}
