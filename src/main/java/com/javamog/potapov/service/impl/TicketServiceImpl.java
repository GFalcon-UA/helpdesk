package com.javamog.potapov.service.impl;

import com.javamog.potapov.dao.*;
import com.javamog.potapov.model.*;
import com.javamog.potapov.model.ticket.*;
import com.javamog.potapov.model.user.User;
import com.javamog.potapov.service.AttachmentService;
import com.javamog.potapov.service.HistoryService;
import com.javamog.potapov.service.TicketService;
import com.javamog.potapov.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private UserDao userDao;


    @Override
    public Ticket getTicketById(Long id) {
        return ticketDao.getTicketById(id);
    }

    @Override
    public void createNewTicket(Ticket ticket, String category, String dateInString,
                                MultipartFile file, String commentText) {


        User user = userDao.getUser(UserUtils.getCurrentUser().getUsername());
        Date desiredDate = DateUtils.parseDate(dateInString);
        ticket.setStatus(TicketStatus.NEW);
        ticket.setCategory(TicketCategory.getByDescription(category));
        ticket.setDesiredDate(desiredDate);
        ticket.addHistoryRecord(historyService.create());
        ticket.setCreatedOn(new Date());
        ticket.setOwner(user);
        if (!StringUtils.isEmpty(commentText)) {
            ticket.addComment(createComment(user, commentText, desiredDate));
        }
        if (file.getSize() != 0) {
            ticket.addAttachment(attachmentService.createAttachment(file));
            ticket.addHistoryRecord(historyService.fileAttached(file.getName()));
        }
        ticketDao.saveTicket(ticket);
    }

    private Comment createComment(User user, String commentText, Date desiredDate) {
        Comment comment = new Comment();
        comment.setCommentUser(user);
        comment.setText(commentText);
        comment.setCommentDate(desiredDate);
        return comment;
    }

    @Override
    public void editTicket(Ticket editedTicket) {
        editedTicket.addHistoryRecord(historyService.edit());
        ticketDao.updateTicket(editedTicket);
    }

    @Override
    public ResponseEntity<List<Ticket>> getTickets(String username) {
        User user = userDao.getUser(username);
        List<Ticket> result = getAllTickets(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public void changeStatus(Ticket ticket, TicketStatus status) {

    }

    private List<Ticket> getAllTickets(User user) {
        List<Ticket> result = new ArrayList<>();
        if (UserRole.EMPLOYEE.equals(user.getRole())) {
            result.addAll(user.getOwnTickets());
        } else if (UserRole.MANAGER.equals(user.getRole())) {
            result.addAll(user.getOwnTickets());
            result.addAll(ticketDao.getTicketsForManager(user.getId()));
        } else if (UserRole.ENGINEER.equals(user.getRole())) {
            result.addAll(ticketDao.getTicketForEngineer(user.getId()));
        }
        return result;
    }
}
