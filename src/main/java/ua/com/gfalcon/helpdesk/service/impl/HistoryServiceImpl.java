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

package ua.com.gfalcon.helpdesk.service.impl;

import ua.com.gfalcon.helpdesk.dao.HistoryDAO;
import ua.com.gfalcon.helpdesk.domain.History;
import ua.com.gfalcon.helpdesk.domain.Ticket;
import ua.com.gfalcon.helpdesk.domain.User;
import ua.com.gfalcon.helpdesk.domain.enums.State;
import ua.com.gfalcon.helpdesk.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryDAO historyDAO;


    @Override
    public List<History> getHistoriesByTicket(Ticket ticket) {
        return historyDAO.findAllBy("ticket", ticket);
    }


    @Override
    public void createTicket(User user, Ticket ticket) {
        History history = new History();
        history.setAction("Ticket is created");
        history.setDescription("Ticket is created");
        user.addHistories(history);
        ticket.addHistories(history);
        historyDAO.saveOrUpdate(history);
    }


    @Override
    public void editTicket(User user, Ticket ticket) {
        History history = new History();
        history.setAction("Ticket is edited");
        history.setDescription("Ticket is edited");
        user.addHistories(history);
        ticket.addHistories(history);
        historyDAO.saveOrUpdate(history);
    }


    @Override
    public void changeStatus(User user, Ticket ticket, State oldState) {
        History history = new History();
        history.setAction("Ticket Status is changed");
        history.setDescription(String.format("Ticket Status is changed from %s to %s", oldState.getStatus(), ticket.getState().getStatus()));
        user.addHistories(history);
        ticket.addHistories(history);
        historyDAO.saveOrUpdate(history);
    }


    @Override
    public void attachFile(User user, Ticket ticket, String fileName) {
        History history = new History();
        history.setAction("File is attached");
        history.setDescription("File is attached: " + fileName);
        user.addHistories(history);
        ticket.addHistories(history);
        historyDAO.saveOrUpdate(history);
    }


    @Override
    public void removeFile(User user, Ticket ticket, String fileName) {
        History history = new History();
        history.setAction("File is removed");
        history.setDescription("File is removed: " + fileName);
        user.addHistories(history);
        ticket.addHistories(history);
        historyDAO.saveOrUpdate(history);
    }

}
