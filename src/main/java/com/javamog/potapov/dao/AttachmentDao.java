package com.javamog.potapov.dao;

import com.javamog.potapov.model.Attachment;

public interface AttachmentDao {

    void saveAttachment(Attachment attachment);

    void deleteAttachment(Attachment attachment);

}
