package com.javamog.potapov.model.ticket;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ATTACHMENT")
public class Attachment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATTACHMENT_ID", unique = true)
    private Long id;

    @Column(name = "ATTACHMENT_name")
    private String attachmentName;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name = "blob", nullable=false)
    private byte[] content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
