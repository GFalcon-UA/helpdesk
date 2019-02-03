/*
 *  MIT License
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

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.com.gfalcon.helpdesk.domain.Ticket;
import ua.com.gfalcon.helpdesk.domain.enums.Urgency;
import ua.com.gfalcon.helpdesk.service.TicketService;
import ua.com.gfalcon.helpdesk.service.UserService;



@RunWith(MockitoJUnitRunner.class)
public class TicketControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TicketService ticketService;

    @Mock
    private UserService userService;


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new TicketController(ticketService, userService)).build();
    }


    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void getTicketsList() {
    }


    @Test
    public void getTicket() throws Exception {
        Long ticketId = 1L;
        Ticket ticket = createTicket(ticketId);

        Mockito.when(ticketService.getTicket(ticketId)).thenReturn(ticket);
        

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/tickets/get")
                        .param("nTicketId", ticketId.toString())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasKey("nId")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nId", Matchers.hasToString(ticketId.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nId", Matchers.hasToString(ticket.getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasKey("sName")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sName", Matchers.equalTo(ticket.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasKey("sDescription")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sDescription", Matchers.equalTo(ticket.getDescription())))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasKey("sUrgency")))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasKey("aoHistory")))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasKey("afAttachments")))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasKey("aoComments")));

        ArgumentCaptor<Long> ticketIdCaptor = ArgumentCaptor.forClass(Long.class);

        Mockito.verify(ticketService, Mockito.times(1))
                .getTicket(ticketIdCaptor.capture());
        Mockito.verifyNoMoreInteractions(ticketService);

        Assert.assertEquals(ticketId, ticketIdCaptor.getValue());
    }


    private Ticket createTicket(Long id) {
        Ticket ticket = new Ticket();
        ticket.setId(id);
        ticket.setName("Task " + id);
        ticket.setDescription("Description of the Task " + id);
        ticket.setUrgency(Urgency.MEDIUM);
        return ticket;
    }


    @Test
    public void createTicket() {
    }


    @Test
    public void updateTicket() {
    }


    @Test
    public void addComment() {
    }


    @Test
    public void changeTicketState() {
    }


    @Test
    public void getFeedback() {
    }


    @Test
    public void setFeedback() {
    }

}