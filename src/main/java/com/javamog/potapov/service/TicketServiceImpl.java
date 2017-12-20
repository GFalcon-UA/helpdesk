package com.javamog.potapov.service;

import com.javamog.potapov.dao.*;
import com.javamog.potapov.model.*;
import com.javamog.potapov.utils.AttachmentUtils;
import com.javamog.potapov.utils.CommentUtils;
import com.javamog.potapov.utils.HistoryUtils;
import com.javamog.potapov.utils.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private AttachmentDao attachmentDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private HistoryDao historyDao;

    private static final String TICKET_IS_CREATED = "Ticket is created";
    private static final String FILE_IS_ATTACHED = "File is attached";
    private static final String TICKET_IS_EDITED = "Ticket is edited";
    private static final String TICKET_STATUS_CHANGED = "Ticket status is changed";


    @Override
    public void saveTicket(Ticket ticket) {
        ticketDao.saveTicket(ticket);
    }



    @Override
    @Transactional
    public List<Ticket> createNewTicket(Ticket ticket, String category, String dateInString,
                                        MultipartFile file, String commentText) {


        User user = userDao.getUser();

        ticket.setCategory(categoryDao.getCategoryByName(category));

        History createHistory = HistoryUtils.addHistoryRecord(user, ticket, TICKET_IS_CREATED, TICKET_IS_CREATED);
        historyDao.saveHistory(createHistory);


        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateInString);
            ticket.setDesiredDate(date);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        ticket.setCreatedOn(new Date());
        ticket.setOwner(user);

        if (file.getSize() != 0) {
            Attachment attachment = AttachmentUtils.setAttachment(ticket, file);
            attachmentDao.saveAttachment(attachment);
            History attachmentHistory = HistoryUtils.addHistoryRecord(user, ticket, FILE_IS_ATTACHED,
                    FILE_IS_ATTACHED + ": " + file.getName());
            historyDao.saveHistory(attachmentHistory);
        }

        Comment comment = CommentUtils.setComment(user, ticket, commentText);
        commentDao.saveComment(comment);

        ticketDao.saveTicket(ticket);

        List<Attachment> attachments = ticket.getTicketAttachments();

        return user.getOwnTickets();
    }

    @Override
    @Transactional
    public List<Ticket> editTicket(Ticket ticket, String category, String dateInString,
                                        MultipartFile file, String commentText) {
        User user = userDao.getUser();

        if (category != null) {
            ticket.setCategory(categoryDao.getCategoryByName(category));
        }

        History editHistory = HistoryUtils.addHistoryRecord(user, ticket, TICKET_IS_EDITED, TICKET_IS_EDITED);
        historyDao.saveHistory(editHistory);

        if (ticket.getState().equals(State.NEW)) {
            History statusHistory = HistoryUtils.addHistoryRecord(user, ticket, TICKET_STATUS_CHANGED,
                    TICKET_STATUS_CHANGED + "from " + State.DRAFT + "to" + State.NEW);
            historyDao.saveHistory(statusHistory);
        }

        if (dateInString != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(dateInString);
                ticket.setDesiredDate(date);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        if (file.getSize() != 0) {
            Attachment attachment = AttachmentUtils.setAttachment(ticket, file);
            attachmentDao.saveAttachment(attachment);
            History attachmentHistory = HistoryUtils.addHistoryRecord(user, ticket, FILE_IS_ATTACHED,
                    FILE_IS_ATTACHED + ": " + file.getName());
            historyDao.saveHistory(attachmentHistory);
        }

        if (commentText != null) {
            Comment comment = CommentUtils.setComment(user, ticket, commentText);
            commentDao.saveComment(comment);
        }

        ticketDao.updateTicket(ticket);

        return user.getOwnTickets();
    }
}
