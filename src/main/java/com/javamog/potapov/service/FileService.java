
package com.javamog.potapov.service;

public interface FileService {
    Long addAttachment(Long ticketId, byte[] fileContent, String fileName, Long userId);

    byte[] getAttachmentById(Long attachmentId);

    void removeAttachmentById(Long attachmentId, Long userId);
}
