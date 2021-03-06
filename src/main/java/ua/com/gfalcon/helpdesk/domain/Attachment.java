/*
 *  MIT License
 * -----------
 *
 * Copyright (c) 2016-2019 Oleksii V. KHALIKOV, PE (gfalcon.com.ua)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ua.com.gfalcon.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gfalcon.utils.repository.entity.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;



@Entity
@Table(name = "ATTACHMENTS")
public class Attachment extends AbstractEntity implements Serializable {

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "blob", nullable = false)
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
