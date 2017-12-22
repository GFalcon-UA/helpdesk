package com.javamog.potapov.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private int id;

    @Column(name = "text")
    private String text;

    @Column(name = "commentDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date commentDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User commentUser;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket commentTicket;

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public User getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(User commentUser) {
        this.commentUser = commentUser;
    }

    public Ticket getCommentTicket() {
        return commentTicket;
    }

    public void setCommentTicket(Ticket commentTicket) {
        this.commentTicket = commentTicket;
    }
}

