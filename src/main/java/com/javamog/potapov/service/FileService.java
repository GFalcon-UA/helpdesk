
package com.javamog.potapov.service;

public interface FileService {
    Long addAttachment(Long ticketId, byte[] fileContent);

    byte[] getAttachmentById(Long attachmentId);

    void removeAttachmentById(Long attachmentId);
}
