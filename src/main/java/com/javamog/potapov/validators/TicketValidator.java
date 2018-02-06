package com.javamog.potapov.validators;

import com.javamog.potapov.domain.Ticket;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;

public class TicketValidator implements Validator {

    private StringValidator strValidator = StringValidator.getValidator();

    @Override
    public boolean supports(Class<?> aClass) {
        return Ticket.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Ticket ticket = (Ticket) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
        if(!strValidator.init(ticket.getName()).onlyLower().onlyDigit().onlySpecial().maxSize(100).validate()){
            errors.reject("Incorrect Tickets name");
        }
        if(ticket.getDescription() != null && !ticket.getDescription().isEmpty()){
            if(!strValidator.init(ticket.getDescription()).onlyUpper().onlyLower().onlyDigit().onlySpecial().maxSize(500).validate()){
                errors.reject("Incorrect Tickets description");
            }
        }
        if(ticket.getDesiredDate() != null && ticket.getDesiredDate().before(new Date())){
            errors.reject("You can't set DesiredDate that is less then current Date");
        }
        if(ticket.getComments() != null && !ticket.getComments().isEmpty()){
            if(!strValidator.init(ticket.getDescription()).onlyUpper().onlyLower().onlyDigit().maxSize(500).validate()){
                errors.reject("Incorrect tickets comment");
            }
        }
    }
}
