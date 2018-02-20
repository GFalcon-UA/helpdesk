
package com.javamog.potapov.service;

public interface FileService {
    Long addAttachment(Long ticketId, Long userId, String fileName, String mimeType, byte[] content);

    byte[] getAttachmentById(Long attachmentId);

    void removeAttachmentById(Long attachmentId, Long userId);
}
