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

package ua.com.gfalcon.helpdesk.controller;

import ua.com.gfalcon.helpdesk.domain.Comment;
import ua.com.gfalcon.helpdesk.domain.Feedback;
import ua.com.gfalcon.helpdesk.domain.Ticket;
import ua.com.gfalcon.helpdesk.domain.User;
import ua.com.gfalcon.helpdesk.dto.models.TicketDTO;
import ua.com.gfalcon.helpdesk.dto.util.DTOConverter;
import ua.com.gfalcon.helpdesk.json.JsonRestUtils;
import ua.com.gfalcon.helpdesk.service.TicketService;
import ua.com.gfalcon.helpdesk.service.UserService;
import ua.com.gfalcon.helpdesk.validators.TicketValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    public ResponseEntity getTicketsList(@RequestParam(name = "nUserId") Long userId) {

        //        User user = userService.findLoggedInUser();
        User user = userService.getUserById(userId);
        log.info(user);

        Set<Ticket> ticketList = new TreeSet<>();

        ticketList = ticketService.getTicketList(user);

        return JsonRestUtils.toJsonResponse(ticketList);
    }


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity getTicket(@RequestParam(name = "nTicketId") Long ticketId) {
        Ticket ticket = ticketService.getTicket(ticketId);
        return JsonRestUtils.toJsonResponse(ticket);
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity createTicket(@RequestParam(name = "nUserId") Long userId,
                                       @RequestBody String body, Errors errors) {

        return saveTicket(body, userId, errors);
    }


    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity updateTicket(@RequestParam(name = "nUserId") Long userId,
                                       @RequestBody String body, Errors errors) {

        return saveTicket(body, userId, errors);
    }


    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public ResponseEntity addComment(@RequestParam(name = "nUserId") Long userId,
                                     @RequestParam(name = "nTicketId") Long ticketId,
                                     @RequestBody String body, Errors errors) {

        Ticket resultTicket = ticketService.getTicket(ticketId);

        HashMap<String, String> parsedContent = new HashMap<>();
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
                                            @RequestParam(name = "nTicketId") Long ticketId, @RequestParam(name = "sNewState") String state) {

        return JsonRestUtils.toJsonResponse(ticketService.setNewState(ticketId, userId, state));
    }


    @RequestMapping(value = "/getFeedback", method = RequestMethod.GET)
    public ResponseEntity getFeedback(@RequestParam(name = "nTicketId") Long ticketId) {

        return JsonRestUtils.toJsonResponse(ticketService.getFeedback(ticketId));
    }


    @RequestMapping(value = "/setFeedback", method = RequestMethod.POST)
    public ResponseEntity setFeedback(@RequestParam(name = "nUserId") Long userId,
                                      @RequestParam(name = "nTicketId") Long ticketId,
                                      @RequestBody String json) {

        Feedback feedback = ticketService.setFeedback(ticketId, userId, DTOConverter.parseFeedback(json));

        return JsonRestUtils.toJsonResponse(feedback);
    }


    private ResponseEntity saveTicket(String body, Long userId, Errors errors) {
        Long categoryId = parseCategoryId(body);
        TicketDTO ticketDTO = DTOConverter.parseTicket(body);

        TicketValidator validator = new TicketValidator();
        validator.validate(ticketDTO, errors);

        if (errors.hasErrors()) {
            List<ObjectError> allErrors = errors.getAllErrors();

            StringBuilder message = new StringBuilder();
            for (ObjectError err : allErrors) {
                if (message.length() > 0) {
                    message.append("; ");
                }
                message.append(err.getCode());
            }
            return JsonRestUtils.toJsonResponse(HttpStatus.NOT_IMPLEMENTED, message.toString());
        }

        Ticket resultTicket = null;
        resultTicket = ticketService.saveTicket(ticketDTO, userId, categoryId);

        HashMap<String, Object> ticketBody = new HashMap<String, Object>();
        ticketBody = JsonRestUtils.readObject(body, ticketBody.getClass());
        if (ticketBody.containsKey("sComment")) {
            String text = (String) ticketBody.get("sComment");
            Comment comment = ticketService.addComment(resultTicket, text, userId);
            resultTicket.addComment(comment);
        }

        return JsonRestUtils.toJsonResponse(resultTicket);
    }


    private Long parseCategoryId(String jsonBody) {
        HashMap<String, Object> ticketBody = new HashMap<String, Object>();
        ticketBody = JsonRestUtils.readObject(jsonBody, ticketBody.getClass());
        Long categoryId = null;
        if (ticketBody.containsKey("nCategory")) {
            Object num = ticketBody.get("nCategory");
            if (num instanceof Integer) {
                Integer integer = (Integer) num;
                categoryId = integer.longValue();
            } else if (num instanceof Long) {
                categoryId = (Long) num;
            } else {
                categoryId = Long.parseLong((String) num);
            }
        }
        return categoryId;
    }

}
