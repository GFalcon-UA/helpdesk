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

package ua.com.gfalcon.helpdesk.validators;

import ua.com.gfalcon.helpdesk.domain.Ticket;
import ua.com.gfalcon.helpdesk.dto.models.TicketDTO;
import org.springframework.validation.Errors;
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
        TicketDTO ticket = (TicketDTO) o;

//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
        if (!strValidator.init(ticket.getName()).onlyLower().onlyDigit().onlySpecial().maxSize(100).validate()) {
            errors.reject("Incorrect Tickets name");
        }
        if (ticket.getDescription() != null && !ticket.getDescription().isEmpty()) {
            if (!strValidator.init(ticket.getDescription()).onlyUpper().onlyLower().onlyDigit().onlySpecial().maxSize(500).validate()) {
                errors.reject("Incorrect Tickets description");
            }
        }
        if (ticket.getDesiredDate() != null && ticket.getDesiredDate().before(new Date())) {
            errors.reject("You can't set DesiredDate that is less then current Date");
        }
//        if(ticket.getComments() != null && !ticket.getComments().isEmpty()){
//            if(!strValidator.init(ticket.getDescription()).onlyUpper().onlyLower().onlyDigit().maxSize(500).validate()){
//                errors.reject("Incorrect tickets comment");
//            }
//        }
    }

}
