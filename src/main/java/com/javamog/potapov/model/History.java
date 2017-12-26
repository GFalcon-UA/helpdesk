package com.javamog.potapov.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "HISTORY")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORY_ID", unique = true)
    private Long id;

    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date historyDate;

    @Column(name = "action")
    private String action;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User historyUser;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket historyTicket;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(Date date) {
        this.historyDate = date;
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

    public User getHistoryUser() {
        return historyUser;
    }

    public void setHistoryUser(User historyUser) {
        this.historyUser = historyUser;
    }

    public Ticket getHistoryTicket() {
        return historyTicket;
    }

    public void setHistoryTicket(Ticket historyTicket) {
        this.historyTicket = historyTicket;
    }
}
