
/*
 *  MIT License
 * -----------
 *
 * Copyright (c) 2016-2019 Oleksii V. KHALIKOV, PE (gfalcon.com.ua)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ua.com.gfalcon.helpdesk.service.impl;

import ua.com.gfalcon.helpdesk.dao.AttachmentDAO;
import ua.com.gfalcon.helpdesk.dao.TicketDAO;
import ua.com.gfalcon.helpdesk.dao.UserDAO;
import ua.com.gfalcon.helpdesk.domain.Attachment;
import ua.com.gfalcon.helpdesk.domain.Ticket;
import ua.com.gfalcon.helpdesk.domain.User;
import ua.com.gfalcon.helpdesk.service.FileService;
import ua.com.gfalcon.helpdesk.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Set;



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
    public Long addAttachment(Long ticketId, Long userId, String fileName, String mimeType, byte[] content) {
        User user = userDAO.findByIdExpected(userId);
        Ticket ticket = ticketDAO.findByIdExpected(ticketId);
        Attachment attachment = new Attachment();
        attachment.setContent(content);
        attachment.setFileName(fileName);
        attachment.setMimeType(mimeType);
        Attachment result;
        if (ticket.getAttachments().contains(attachment)) {
            Set<Attachment> attachments = ticket.getAttachments();
            result = attachments.stream().filter(attach -> Arrays.equals(attach.getContent(), content)).findFirst().get();
        } else {
            ticket.addAttachments(attachment);

            result = attachmentDAO.saveOrUpdate(attachment);
            //        ticketDAO.saveOrUpdate(ticket);

            historyService.attachFile(user, ticket, fileName);
        }

        return result.getId();
    }


    @Override
    public byte[] getAttachmentById(Long attachmentId) {
        Attachment byIdExpected = attachmentDAO.findByIdExpected(attachmentId);
        return byIdExpected.getContent();
    }


    @Override
    public void removeAttachmentById(Long attachmentId, Long userId) {
        User user = userDAO.findByIdExpected(userId);
        Attachment attachment = attachmentDAO.findByIdExpected(attachmentId);
        String fileName = attachment.getFileName();
        Ticket ticket = attachment.getTicket();
        ticket.removeAttachments(attachment);

        attachmentDAO.delete(attachmentId);
//        ticketDAO.saveOrUpdate(ticket);

        historyService.attachFile(user, ticket, fileName);
    }

}
