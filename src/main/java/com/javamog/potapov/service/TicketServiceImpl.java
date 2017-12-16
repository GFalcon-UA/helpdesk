package com.javamog.potapov.service;

import com.javamog.potapov.dao.*;
import com.javamog.potapov.model.*;
import com.javamog.potapov.utils.AttachmentUtils;
import com.javamog.potapov.utils.CommentUtils;
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

    @Override
    public void saveTicket(Ticket ticket) {
        ticketDao.saveTicket(ticket);
    }

    @Override
    public List<Ticket> createNewTicket(Ticket ticket, String category, String dateInString,
                                        MultipartFile file, String commentText) {

        User user = userDao.getUser();
        ticket.setCategory(categoryDao.getCategoryByName(category));

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateInString);
            ticket.setDesiredDate(date);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        ticket.setCreatedOn(new Date());
        ticket.setOwner(user);
        //ticket.setState(State.NEW);

        Attachment attachment = AttachmentUtils.setAttachment(ticket, file);
        attachmentDao.saveAttachment(attachment);

        Comment comment = CommentUtils.setComment(user, ticket, commentText);
        commentDao.saveComment(comment);

        ticketDao.saveTicket(ticket);
        userDao.updateUser(user);


        return user.getOwnTickets();
    }

    @Override
    public List<Ticket> editTicket(Ticket ticket, String category, String dateInString,
                                        MultipartFile file, String commentText) {
        User user = userDao.getUser();

        if (category != null) {
            ticket.setCategory(categoryDao.getCategoryByName(category));
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

        if (file != null) {
            Attachment attachment = AttachmentUtils.setAttachment(ticket, file);
            attachmentDao.saveAttachment(attachment);
        }

        if (commentText != null) {
            Comment comment = CommentUtils.setComment(user, ticket, commentText);
            commentDao.saveComment(comment);
        }

        //userDao.updateUser(user);
        ticketDao.updateTicket(ticket);

        return user.getOwnTickets();
    }
}
