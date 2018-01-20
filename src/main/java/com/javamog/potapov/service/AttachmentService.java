package com.javamog.potapov.service;

import com.javamog.potapov.model.ticket.Attachment;
import com.javamog.potapov.model.ticket.Ticket;
import com.javamog.potapov.model.user.User;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {

    void deleteAttachment(Attachment attachment, User user, Ticket ticket);

    Attachment createAttachment(MultipartFile file);
}
