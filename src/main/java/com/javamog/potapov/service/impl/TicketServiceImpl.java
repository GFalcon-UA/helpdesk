package com.javamog.potapov.service.impl;

import com.javamog.potapov.dao.*;
import com.javamog.potapov.domain.*;
import com.javamog.potapov.domain.enums.Role;
import com.javamog.potapov.domain.enums.State;
import com.javamog.potapov.dto.models.FeedbackDTO;
import com.javamog.potapov.dto.models.TicketDTO;
import com.javamog.potapov.mail.MailSender;
import com.javamog.potapov.service.HistoryService;
import com.javamog.potapov.service.TicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
    private static final Logger log = LogManager.getLogger(TicketServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private FeedbackDAO feedbackDAO;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private HistoryService historyService;


    @Override
    @Transactional(readOnly = true)
    public Set<Ticket> getTicketList(User currentUser) {
        Set<Ticket> result = new HashSet<>();
        if(currentUser.getRole().equals(Role.EMPLOYEE)){
            result.addAll(ticketDAO.findAllBy("owner", currentUser));
        } else if (currentUser.getRole().equals(Role.MANAGER)){
            result.addAll(ticketDAO.findAllBy("owner", currentUser));
            result.addAll(ticketDAO.findAllBy("state", State.NEW).stream()
                    .filter(o -> o.getOwner().getRole().equals(Role.EMPLOYEE)).collect(
                    Collectors.toSet()));
            result.addAll(ticketDAO.findAllBy("approver", currentUser).stream()
                    .filter(o -> o.getState().equals(State.APPROVED)
                            || o.getState().equals(State.DECLINED)
                            || o.getState().equals(State.CANCELED)
                            || o.getState().equals(State.IN_PROGRESS)
                            || o.getState().equals(State.DONE))
                    .collect(Collectors.toSet()));
        } else if (currentUser.getRole().equals(Role.ENGINEER)){
            result.addAll(ticketDAO.findAllBy("state", State.APPROVED).stream()
                    .filter(o -> o.getOwner().getRole().equals(Role.EMPLOYEE)
                            || o.getOwner().getRole().equals(Role.MANAGER))
                    .collect(Collectors.toSet()));
            result.addAll(ticketDAO.findAllBy("assignee", currentUser).stream()
                    .filter(o -> o.getState().equals(State.IN_PROGRESS)
                            || o.getState().equals(State.DONE))
                    .collect(Collectors.toSet()));
        } else {
            log.warn("Role isn't equal");
        }
        return result;
    }

    @Override
    public Ticket saveTicket(TicketDTO ticketDTO, Long userId, Long categoryId) {

        User user = userDAO.findByIdExpected(userId);
        Category category = categoryDAO.findByIdExpected(categoryId);
        Ticket ticket;
        Ticket result;

        if(ticketDTO.getId() == null){
            ticket = getCreatedTicket(ticketDTO, category, user);
            ticket.setCreatedOn(new Date());
            result = ticketDAO.saveOrUpdate(ticket);
            historyService.createTicket(user, result);
        } else {
            ticket = getUpdatedTicket(ticketDTO, category);
            result = ticketDAO.saveOrUpdate(ticket);
            historyService.editTicket(user, ticket);
        }

        mailSender.sentNotification(result, State.DRAFT);
        return result;
    }

    private Ticket getCreatedTicket(TicketDTO ticketDTO, Category category, User user){
        Ticket ticket = new Ticket();
        ticket.setCreatedOn(new Date());
        user.addOwnTicket(ticket);
        mergeTicket(ticket, ticketDTO, category);

        return ticket;
    }

    private Ticket getUpdatedTicket(TicketDTO ticketDTO, Category category){
        Ticket ticket = ticketDAO.findByIdExpected(ticketDTO.getId());
        mergeTicket(ticket, ticketDTO, category);
        return ticket;
    }

    private void mergeTicket(Ticket ticket, TicketDTO ticketDTO, Category category){
        if(ticket.getCategory() == null || !ticket.getCategory().equals(category)){
            if(ticket.getCategory() != null){
                ticket.getCategory().removeTicket(ticket);
            }
            category.addTicket(ticket);
        }

        if(ticketDTO.getName() != null){
            ticket.setName(ticketDTO.getName());
        }
        if(ticketDTO.getDescription() != null){
            ticket.setDescription(ticketDTO.getDescription());
        }
        if(ticketDTO.getUrgency() != null){
            ticket.setUrgency(ticketDTO.getUrgency());
        }
        if(ticketDTO.getDesiredDate() != null){
            ticket.setDesiredDate(ticketDTO.getDesiredDate());
        }
        if(ticketDTO.getState() != null){
            ticket.setState(ticketDTO.getState());
        }

    }

    @Override
    public Comment addComment(Ticket ticket, String comment, Long userId) {
        if(comment == null || comment.isEmpty()){
            return null;
        }
        User user = userDAO.findByIdExpected(userId);
        Comment result = new Comment(comment);
        result.setDate(new Date());

        user.addComment(result);
        ticket.addComment(result);

        commentDAO.saveOrUpdate(result);

        return result;
    }

    @Override
    public Ticket getTicket(Long ticketId) {
        return ticketDAO.findByIdExpected(ticketId);
    }

    @Override
    public Ticket setNewState(Long ticketId, Long userId, String state){
        Ticket ticket = getTicket(ticketId);
        User user = userDAO.findByIdExpected(userId);
        State oldState = ticket.getState();
        State newState = State.valueOf(state);
        if(newState.equals(State.APPROVED)){
            user.addApproveTicket(ticket);
        }
        if(newState.equals(State.IN_PROGRESS)){
            user.setAssignTicket(ticket);
        }
        ticket.setState(newState);
        Ticket result = ticketDAO.saveOrUpdate(ticket);

        historyService.changeStatus(user, result, oldState);

        mailSender.sentNotification(result, oldState);

        return result;
    }

    @Override
    public Feedback setFeedback(Long ticketId, Long userId, FeedbackDTO feedbackDTO) {
        Ticket ticket = getTicket(ticketId);
        User user = userDAO.findByIdExpected(userId);
        Feedback feedback = new Feedback();

        feedback.setRate(feedbackDTO.getRate());
        feedback.setText(feedbackDTO.getText());
        user.addFeedback(feedback);
        ticket.setFeedback(feedback);

        Feedback result = feedbackDAO.saveOrUpdate(feedback);
        mailSender.sentFeedBackNotification(ticket);

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Feedback getFeedback(Long ticketId) {
        Ticket ticket = getTicket(ticketId);
        Feedback result = ticket.getFeedback();
        if(result == null){
            result = new Feedback();
            ticket.setFeedback(result);
        }
        return result;
    }
}
