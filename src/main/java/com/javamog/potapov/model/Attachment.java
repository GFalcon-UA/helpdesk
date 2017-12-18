package com.javamog.potapov.model;


import javax.persistence.*;

@Entity
@Table(name = "ATTACHMENT")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATTACHMENT_ID")
    private int id;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name = "blob", nullable=false)
    private byte[] content;

    /*@ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket attachmentTicket;*/

    public int getId() {
        return id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] file) {
        this.content = file;
    }

   /* public Ticket getAttachmentTicket() {
        return attachmentTicket;
    }

    public void setAttachmentTicket(Ticket attackmentTicket) {
        this.attachmentTicket = attackmentTicket;
    }*/


}
