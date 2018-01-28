package com.javamog.potapov.service.impl;

import com.javamog.potapov.dao.AttachmentDao;
import com.javamog.potapov.model.ticket.Attachment;
import com.javamog.potapov.model.history.History;
import com.javamog.potapov.model.ticket.Ticket;
import com.javamog.potapov.model.user.User;
import com.javamog.potapov.service.AttachmentService;
import com.javamog.potapov.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentDao attachmentDao;

    @Autowired
    private HistoryService historyService;

    @Override
    public void deleteAttachment(Attachment attachment, User user, Ticket ticket) {
        ticket.removeFromAttachments(attachment);
        ticket.addHistoryRecord(historyService.fileRemoved(attachment.getAttachmentName()));
        attachmentDao.deleteAttachment(attachment);
    }

    @Override
    public Attachment createAttachment(MultipartFile file) {
        Attachment attachment = new Attachment();
        try {
            attachment.setContent(file.getBytes());
            attachment.setAttachmentName(file.getName());
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't create attachment");
        }
        return attachment;
    }
}
