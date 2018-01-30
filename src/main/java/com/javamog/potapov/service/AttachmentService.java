package com.javamog.potapov.service;

import com.javamog.potapov.model.Attachment;
import com.javamog.potapov.model.Ticket;
import com.javamog.potapov.model.User;

public interface AttachmentService {

    void deleteAttachment(Attachment attachment, User user, Ticket ticket);
}
