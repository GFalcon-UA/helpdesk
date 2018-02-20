package com.javamog.potapov.dto.util;

import com.javamog.potapov.domain.enums.State;
import com.javamog.potapov.domain.enums.Urgency;
import com.javamog.potapov.dto.models.FeedbackDTO;
import com.javamog.potapov.dto.models.TicketDTO;
import com.javamog.potapov.json.JsonRestUtils;

import java.util.Date;
import java.util.HashMap;

public class DTOConverter {

    public static TicketDTO parseTicket(String json){
        TicketDTO ticket = new TicketDTO();

        HashMap<String, Object> ticketBody =  new HashMap<String, Object>();
        ticketBody = JsonRestUtils.readObject(json, ticketBody.getClass());

        if(ticketBody.containsKey("nId")){
            Long id;
            Object num = ticketBody.get("nId");
            if(num instanceof Integer){
                Integer integer = (Integer) num;
                id = integer.longValue();
            } else if (num instanceof Long){
                id = (Long) num;
            } else {
                id = Long.parseLong((String) num);
            }

            ticket.setId(id);
        }
        if(ticketBody.containsKey("sName")){
            ticket.setName((String) ticketBody.get("sName"));
        }
        if(ticketBody.containsKey("sDescription")){
            ticket.setDescription((String) ticketBody.get("sDescription"));
        }
        if(ticketBody.containsKey("sUrgency")){
            ticket.setUrgency(Urgency.valueOf((String) ticketBody.get("sUrgency")));
        }
        if(ticketBody.containsKey("dDesiredDate")){
            String dDesiredDate = (String) ticketBody.get("dDesiredDate");
            Date desiredDate = DateConverter.convert(dDesiredDate);
            ticket.setDesiredDate(desiredDate);
        }
        if(ticketBody.containsKey("sState")){
            ticket.setState(State.valueOf((String) ticketBody.get("sState")));
        }

        return ticket;
    }

    public static FeedbackDTO parseFeedback(String json){
        FeedbackDTO feedback = new FeedbackDTO();

        HashMap<String, Object> parsedJson = JsonRestUtils.readObject(json, new HashMap<String, Object>().getClass());
        Integer rate = -1;
        if(parsedJson.containsKey("nRate")){
            rate = (Integer) parsedJson.get("nRate");
        }
        String text = "";
        if(parsedJson.containsKey("nRate")){
            text = (String) parsedJson.get("nRate");
        }

        feedback.setRate(rate);
        feedback.setText(text);

        return feedback;
    }


}
