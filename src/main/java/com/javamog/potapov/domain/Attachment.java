package com.javamog.potapov.domain;

import com.javamog.potapov.parent.entity.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ATTACHMENT")
public class Attachment extends AbstractEntity implements Serializable {


    @Column(name = "ATTACHMENT_name")
    private String attachmentName;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name = "blob", nullable=false)
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket attachmentTicket;


}
