package com.javamog.potapov.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javamog.potapov.parent.entity.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ATTACHMENTS")
public class Attachment extends AbstractEntity implements Serializable {

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name = "blob", nullable=false)
    @JsonIgnore
    private byte[] content;

    @ManyToOne
    @JsonBackReference
    @JsonProperty("oTicket")
    private Ticket ticket;

    public Attachment() {
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

    protected void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
