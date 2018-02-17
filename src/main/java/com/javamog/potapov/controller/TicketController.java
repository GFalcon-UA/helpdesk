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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

@RestController
@RequestMapping(value = "api/tickets", produces = "application/json")
public class TicketController {

    private final static Logger log = LogManager.getLogger(TicketController.class);

    @Autowired
    private TicketService ticketService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity getTicketsList(@RequestParam(name = "nUserId") Long userId){

//        User user = userService.findLoggedInUser();
        User user = userService.getUserById(userId);
        log.info(user);

        Set<Ticket> ticketList = new TreeSet<>();

        ticketList = ticketService.getTicketList(user);

        return JsonRestUtils.toJsonResponse(ticketList);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity getTicket(@RequestParam(name = "nTicketId") Long ticketId){
        Ticket ticket = ticketService.getTicket(ticketId);
        return JsonRestUtils.toJsonResponse(ticket);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity createTicket(@RequestParam(name = "nUserId") Long userId,
            @RequestBody String body, Errors errors) {

        return saveTicket(new Ticket(), body, userId, errors);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity updateTicket(@RequestParam(name = "nUserId") Long userId, @RequestParam(name = "nTicketId") Long ticketId,
            @RequestBody String body, Errors errors){

        return saveTicket(ticketService.getTicket(ticketId), body, userId, errors);
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public ResponseEntity addComment(@RequestParam(name = "nUserId") Long userId, @RequestParam(name = "nTicketId") Long ticketId,
            @RequestBody String body, Errors errors){

        Ticket resultTicket = ticketService.getTicket(ticketId);

        HashMap<String, String> parsedContent =  new HashMap<>();
        parsedContent = JsonRestUtils.readObject(body, parsedContent.getClass());
        if (parsedContent.containsKey("sComment")) {
            String text = parsedContent.get("sComment");
            Comment comment = ticketService.addComment(resultTicket, text, userId);
            resultTicket.addComment(comment);
        }

        return JsonRestUtils.toJsonResponse(resultTicket);
    }

    @RequestMapping(value = "/state", method = RequestMethod.PUT)
    public ResponseEntity changeTicketState(@RequestParam(name = "nUserId") Long userId,
            @RequestParam(name = "nTicketId") Long ticketId, @RequestParam(name = "sNewState") String state){

        return JsonRestUtils.toJsonResponse(ticketService.setNewState(ticketId, userId, state));
    }

    private ResponseEntity saveTicket(Ticket targetTicket, String body, Long userId, Errors errors){
        Long categoryId = parseCategoryId(body);
        Ticket ticket = parseTicket(body, targetTicket);

        //        TicketValidator validator = new TicketValidator();
        //        validator.validate(ticket, errors);
        //
        //        if(errors.hasErrors()){
        //            return JsonRestUtils.toJsonErrorResponse(HttpStatus.NOT_IMPLEMENTED, errors.getAllErrors().toString());
        //        }

        Ticket resultTicket = ticketService.createTicket(ticket, userId, categoryId);

        HashMap<String, Object> ticketBody =  new HashMap<String, Object>();
        ticketBody = JsonRestUtils.readObject(body, ticketBody.getClass());
        if (ticketBody.containsKey("sComment")) {
            String text = (String) ticketBody.get("sComment");
            Comment comment = ticketService.addComment(resultTicket, text, userId);
            resultTicket.addComment(comment);
        }

        return JsonRestUtils.toJsonResponse(resultTicket);
    }

    private Long parseCategoryId(String jsonBody){
        HashMap<String, Object> ticketBody =  new HashMap<String, Object>();
        ticketBody = JsonRestUtils.readObject(jsonBody, ticketBody.getClass());
        Long categoryId = null;
        if(ticketBody.containsKey("nCategory")){
            Object num = ticketBody.get("nCategory");
            if(num instanceof Integer){
                Integer integer = (Integer) num;
                categoryId = integer.longValue();
            } else if (num instanceof Long){
                categoryId = (Long) num;
            } else {
                categoryId = Long.parseLong((String) num);
            }
        }
        return categoryId;
    }

    private Ticket parseTicket(String jsonBody, Ticket ticketObjectToSave){
        HashMap<String, Object> ticketBody =  new HashMap<String, Object>();
        ticketBody = JsonRestUtils.readObject(jsonBody, ticketBody.getClass());

        if(ticketBody.containsKey("sName")){
            ticketObjectToSave.setName((String) ticketBody.get("sName"));
        }
        if(ticketBody.containsKey("sDescription")){
            ticketObjectToSave.setDescription((String) ticketBody.get("sDescription"));
        }
        if(ticketBody.containsKey("sUrgency")){
            ticketObjectToSave.setUrgency(Urgency.valueOf((String) ticketBody.get("sUrgency")));
        }
        if(ticketBody.containsKey("dDesiredDate")){
            String dDesiredDate = (String) ticketBody.get("dDesiredDate");
            Date desiredDate = DateConverter.convert(dDesiredDate);
            ticketObjectToSave.setDesiredDate(desiredDate);
        }
        if(ticketBody.containsKey("sState")){
            ticketObjectToSave.setState(State.valueOf((String) ticketBody.get("sState")));
        }
        return ticketObjectToSave;
    }

}
