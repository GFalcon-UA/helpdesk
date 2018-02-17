
package com.javamog.potapov.service.impl;

import com.javamog.potapov.dao.AttachmentDAO;
import com.javamog.potapov.dao.TicketDAO;
import com.javamog.potapov.dao.UserDAO;
import com.javamog.potapov.domain.Attachment;
import com.javamog.potapov.domain.Ticket;
import com.javamog.potapov.domain.User;
import com.javamog.potapov.service.FileService;
import com.javamog.potapov.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FileServiceImpl implements FileService {
    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private AttachmentDAO attachmentDAO;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private UserDAO userDAO;

    @Override
    public Long addAttachment(Long ticketId, byte[] fileContent, String fileName, Long userId) {
        User user = userDAO.findByIdExpected(userId);
        Ticket ticket = ticketDAO.findByIdExpected(ticketId);
        Attachment attachment = new Attachment();
        attachment.setContent(fileContent);
        ticket.addAttachments(attachment);

        attachmentDAO.saveOrUpdate(attachment);
        ticketDAO.saveOrUpdate(ticket);

        historyService.attachFile(user, ticket, fileName);

        return attachment.getId();
    }

    @Override
    public byte[] getAttachmentById(Long attachmentId) {
        Attachment byIdExpected = attachmentDAO.findByIdExpected(attachmentId);
        return byIdExpected.getContent();
    }

    @Override
    public void removeAttachmentById(Long attachmentId, Long userId){
        User user = userDAO.findByIdExpected(userId);
        Attachment attachment = attachmentDAO.findByIdExpected(attachmentId);
        String fileName = attachment.getFileName();
        Ticket ticket = attachment.getTicket();
        ticket.removeAttachments(attachment);

        attachmentDAO.delete(attachmentId);
        ticketDAO.saveOrUpdate(ticket);

        historyService.attachFile(user, ticket, fileName);
    }
}
