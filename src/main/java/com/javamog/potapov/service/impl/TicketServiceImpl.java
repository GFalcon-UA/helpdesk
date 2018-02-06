package com.javamog.potapov.service.impl;

import com.javamog.potapov.dao.CategoryDAO;
import com.javamog.potapov.dao.CommentDAO;
import com.javamog.potapov.dao.TicketDAO;
import com.javamog.potapov.dao.UserDAO;
import com.javamog.potapov.domain.Category;
import com.javamog.potapov.domain.Comment;
import com.javamog.potapov.domain.Ticket;
import com.javamog.potapov.domain.User;
import com.javamog.potapov.domain.enums.Role;
import com.javamog.potapov.domain.enums.State;
import com.javamog.potapov.service.TicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
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


    @Override
    @Transactional(readOnly = true)
    public Set<Ticket> getTicketList(User currentUser) {
        Set<Ticket> result = new TreeSet<>();
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
    public Ticket createTicket(Ticket ticket, Long userId, Long categoryId) {
        User user = userDAO.findByIdExpected(userId);
        Category category = categoryDAO.findByIdExpected(categoryId);

        ticket.setCreatedOn(new Date());

        user.addOwnTicket(ticket);
        category.addTicket(ticket);

        categoryDAO.saveOrUpdate(category);
        Ticket result = ticketDAO.saveOrUpdate(ticket);

        return result;
    }

    @Override
    public Comment addComment(Ticket ticket, String comment, Long userId) {
        if(comment == null || comment.isEmpty()){
            return null;
        }
        User user = userDAO.findByIdExpected(userId);
        Comment result = new Comment(comment);
        result.setDate(new Date());

        user.addComments(result);
        ticket.addComment(result);

        ticketDAO.saveOrUpdate(ticket);
        userDAO.saveOrUpdate(user);
        commentDAO.saveOrUpdate(result);

        return result;
    }




}
