
package com.javamog.potapov.service.impl;

import com.javamog.potapov.dao.AttachmentDAO;
import com.javamog.potapov.dao.TicketDAO;
import com.javamog.potapov.domain.Attachment;
import com.javamog.potapov.domain.Ticket;
import com.javamog.potapov.service.FileService;
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

    @Override
    public Long addAttachment(Long ticketId, byte[] fileContent) {
        Ticket ticket = ticketDAO.findByIdExpected(ticketId);
        Attachment attachment = new Attachment();
        attachment.setContent(fileContent);
        ticket.addAttachments(attachment);

        attachmentDAO.saveOrUpdate(attachment);
        ticketDAO.saveOrUpdate(ticket);

        return attachment.getId();
    }

    @Override
    public byte[] getAttachmentById(Long attachmentId) {
        Attachment byIdExpected = attachmentDAO.findByIdExpected(attachmentId);
        return byIdExpected.getContent();
    }

    @Override
    public void removeAttachmentById(Long attachmentId){
        Attachment attachment = attachmentDAO.findByIdExpected(attachmentId);
        Ticket ticket = attachment.getTicket();
        ticket.removeAttachments(attachment);

        attachmentDAO.delete(attachmentId);
        ticketDAO.saveOrUpdate(ticket);
    }
}
