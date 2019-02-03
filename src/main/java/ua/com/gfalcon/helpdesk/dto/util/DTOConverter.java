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

package ua.com.gfalcon.helpdesk.dto.util;

import ua.com.gfalcon.helpdesk.domain.enums.State;
import ua.com.gfalcon.helpdesk.domain.enums.Urgency;
import ua.com.gfalcon.helpdesk.dto.models.FeedbackDTO;
import ua.com.gfalcon.helpdesk.dto.models.TicketDTO;
import ua.com.gfalcon.helpdesk.json.JsonRestUtils;

import java.util.Date;
import java.util.HashMap;



public class DTOConverter {

    public static TicketDTO parseTicket(String json) {
        TicketDTO ticket = new TicketDTO();

        HashMap<String, Object> ticketBody = new HashMap<String, Object>();
        ticketBody = JsonRestUtils.readObject(json, ticketBody.getClass());

        if (ticketBody.containsKey("nId")) {
            Long id;
            Object num = ticketBody.get("nId");
            if (num instanceof Integer) {
                Integer integer = (Integer) num;
                id = integer.longValue();
            } else if (num instanceof Long) {
                id = (Long) num;
            } else {
                id = Long.parseLong((String) num);
            }

            ticket.setId(id);
        }
        if (ticketBody.containsKey("sName")) {
            ticket.setName((String) ticketBody.get("sName"));
        }
        if (ticketBody.containsKey("sDescription")) {
            ticket.setDescription((String) ticketBody.get("sDescription"));
        }
        if (ticketBody.containsKey("sUrgency")) {
            ticket.setUrgency(Urgency.valueOf((String) ticketBody.get("sUrgency")));
        }
        if (ticketBody.containsKey("dDesiredDate")) {
            String dDesiredDate = (String) ticketBody.get("dDesiredDate");
            Date desiredDate = DateConverter.convert(dDesiredDate);
            ticket.setDesiredDate(desiredDate);
        }
        if (ticketBody.containsKey("sState")) {
            ticket.setState(State.valueOf((String) ticketBody.get("sState")));
        }

        return ticket;
    }


    public static FeedbackDTO parseFeedback(String json) {
        FeedbackDTO feedback = new FeedbackDTO();

        HashMap<String, Object> parsedJson = JsonRestUtils.readObject(json, new HashMap<String, Object>().getClass());
        Integer rate = -1;
        if (parsedJson.containsKey("nRate")) {
            rate = (Integer) parsedJson.get("nRate");
        }
        String text = "";
        if (parsedJson.containsKey("nRate")) {
            text = (String) parsedJson.get("nRate");
        }

        feedback.setRate(rate);
        feedback.setText(text);

        return feedback;
    }


}
