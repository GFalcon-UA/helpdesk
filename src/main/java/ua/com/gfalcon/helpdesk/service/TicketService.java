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

package ua.com.gfalcon.helpdesk.service;

import ua.com.gfalcon.helpdesk.domain.Comment;
import ua.com.gfalcon.helpdesk.domain.Feedback;
import ua.com.gfalcon.helpdesk.domain.Ticket;
import ua.com.gfalcon.helpdesk.domain.User;
import ua.com.gfalcon.helpdesk.dto.models.FeedbackDTO;
import ua.com.gfalcon.helpdesk.dto.models.TicketDTO;

import java.util.Set;



public interface TicketService {

    Set<Ticket> getTicketList(User currentUser);

    Ticket saveTicket(TicketDTO ticket, Long userId, Long categoryId);

    Comment addComment(Ticket ticket, String comment, Long userId);

    Ticket getTicket(Long ticketId);

    Ticket setNewState(Long ticketId, Long userId, String state);

    Feedback setFeedback(Long ticketId, Long userId, FeedbackDTO feedback);

    Feedback getFeedback(Long ticketId);

}
