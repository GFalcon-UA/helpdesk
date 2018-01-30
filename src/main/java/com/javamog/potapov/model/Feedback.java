package com.javamog.potapov.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "FEEDBACK")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FEEDBACK_ID")
    private int id;


    @Column(name = "rate")
    private int rate;


    private Date feedbackDate;

    private String text;

    public int getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User feedbackUser;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket feedbackTicket;

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getFeedbackUser() {
        return feedbackUser;
    }

    public void setFeedbackUser(User feedbackUser) {
        this.feedbackUser = feedbackUser;
    }

    public Ticket getFeedbackTicket() {
        return feedbackTicket;
    }

    public void setFeedbackTicket(Ticket feedbackTicket) {
        this.feedbackTicket = feedbackTicket;
    }
}
