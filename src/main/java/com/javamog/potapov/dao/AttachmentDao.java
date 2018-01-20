package com.javamog.potapov.dao;

import com.javamog.potapov.model.ticket.Attachment;

public interface AttachmentDao {

    void saveAttachment(Attachment attachment);

    void deleteAttachment(Attachment attachment);

    void evictAttachment(Attachment attachment);

}
