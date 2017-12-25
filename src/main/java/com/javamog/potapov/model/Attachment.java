package com.javamog.potapov.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ATTACHMENT")
public class Attachment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATTACHMENT_ID", unique = true)
    private int id;

    @Column(name = "ATTACHMENT_name")
    private String attachmentName;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name = "blob", nullable=false)
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket attachmentTicket;

    public int getId() {
        return id;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] file) {
        this.content = file;
    }

    public Ticket getAttachmentTicket() {
        return attachmentTicket;
    }

    public void setAttachmentTicket(Ticket attackmentTicket) {
        this.attachmentTicket = attackmentTicket;
    }


}
