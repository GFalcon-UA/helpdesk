package com.javamog.potapov.controller;

import com.javamog.potapov.domain.Comment;
import com.javamog.potapov.domain.Ticket;
import com.javamog.potapov.domain.User;
import com.javamog.potapov.domain.enums.State;
import com.javamog.potapov.domain.enums.Urgency;
import com.javamog.potapov.dto.util.DateConverter;
import com.javamog.potapov.json.JsonRestUtils;
import com.javamog.potapov.service.TicketService;
import com.javamog.potapov.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

@Controller
@RequestMapping("api/tickets")
public class TicketController {

    private final static Logger log = LogManager.getLogger(TicketController.class);

    @Autowired
    private TicketService ticketService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity getTicketsList(@RequestParam(name = "nUserId") Long userId){

//        User user = userService.findLoggedInUser();
        User user = userService.getUserById(userId);
        log.info(user);

        Set<Ticket> ticketList = new TreeSet<>();

        ticketList = ticketService.getTicketList(user);

        return JsonRestUtils.toJsonResponse(ticketList);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity createTicket(@RequestParam(name = "nUserId") Long userId,
            @RequestBody String body, Errors errors) {

        //TicketDTO ticket = JsonRestUtils.readObject(body, TicketDTO.class);
        HashMap<String, Object> ticketBody =  new HashMap<String, Object>();
        ticketBody = JsonRestUtils.readObject(body, ticketBody.getClass());
        /*
        {
        category: newTicket.category,
        name: newTicket.name,
        description: newTicket.description,
        urgency: newTicket.urgency,
        desiredDate: newTicket.desiredDate,
        comments: newTicket.comments,
        state: isDraft ? 'DRAFT' : 'NEW'
      }
         */
        Ticket ticket = new Ticket();
        Long categoryId = null;
        if(ticketBody.containsKey("category")){
            Object num = ticketBody.get("category");
            if(num instanceof Integer){
                Integer integer = (Integer) num;
                categoryId = integer.longValue();
            } else if (num instanceof Long){
                categoryId = (Long) num;
            } else {
                categoryId = Long.parseLong((String) num);
            }

        }
        if(ticketBody.containsKey("name")){
            ticket.setName((String) ticketBody.get("name"));
        }
        if(ticketBody.containsKey("description")){
            ticket.setDescription((String) ticketBody.get("description"));
        }
        if(ticketBody.containsKey("urgency")){
            ticket.setUrgency(Urgency.valueOf((String) ticketBody.get("urgency")));
        }
        if(ticketBody.containsKey("desiredDate")){
            ticket.setDesiredDate(DateConverter.convert((String) ticketBody.get("desiredDate")));
        }
        if(ticketBody.containsKey("state")){
            ticket.setState(State.valueOf((String) ticketBody.get("state")));
        }

//        TicketValidator validator = new TicketValidator();
//        validator.validate(ticket, errors);
//
//        if(errors.hasErrors()){
//            return JsonRestUtils.toJsonErrorResponse(HttpStatus.NOT_IMPLEMENTED, errors.getAllErrors().toString());
//        }

        Ticket resultTicket = ticketService.createTicket(ticket, userId, categoryId);

        if (ticketBody.containsKey("comments")) {
            String text = (String) ticketBody.get("comments");
            Comment comment = ticketService.addComment(resultTicket, text, userId);
            resultTicket.addComment(comment);
        }

        return JsonRestUtils.toJsonResponse(resultTicket);
    }

}
