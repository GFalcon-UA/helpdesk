package com.javamog.potapov.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javamog.potapov.parent.entity.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

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

    @JsonProperty("sFileName")
    private String fileName;

    @JsonProperty("sMIMEtype")
    private String mimeType;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "ticket=" + ticket.getId() +
                ", fileName='" + fileName + '\'' +
                ", mimeType='" + mimeType + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Attachment))
            return false;
        Attachment that = (Attachment) o;
        return Arrays.equals(getContent(), that.getContent()) &&
                Objects.equals(getTicket(), that.getTicket());
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(getTicket());
        result = 31 * result + Arrays.hashCode(getContent());
        return result;
    }


}
