package com.javamog.potapov.domain;

import com.javamog.potapov.parent.entity.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ATTACHMENT")
public class Attachment extends AbstractEntity implements Serializable {

//    @Column(name = "ATTACHMENT_name")
    private String name;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name = "blob", nullable=false)
    private byte[] content;

//    @ManyToOne
//    @JoinColumn(name = "ticket_id")
    @ManyToOne
//    @JsonIgnore
    private Ticket ticket;

    public Attachment() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Ticket getTicket() {
        return ticket;
    }

    @Deprecated
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
