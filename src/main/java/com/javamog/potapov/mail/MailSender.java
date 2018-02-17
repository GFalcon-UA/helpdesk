package com.javamog.potapov.mail;

import com.javamog.potapov.domain.Ticket;
import com.javamog.potapov.domain.User;
import com.javamog.potapov.domain.enums.Role;
import com.javamog.potapov.domain.enums.State;
import com.javamog.potapov.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
@Scope("session")
public class MailSender {

    private static final Logger log = LogManager.getLogger(MailSender.class);

    // todo
    private static final String myMail = "wealth.is.goal@gmail.com";
    private static final String pass = "max3521267";
    private static final String host = "http://localhost:8080";

    @Autowired
    private UserService userService;

    private Session mailSession;
    private Transport tr;

    public MailSender(){
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtps");
        props.setProperty("mail.smtps.auth", "true");
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.user", myMail);

        mailSession = Session.getDefaultInstance(props);

        try {
            tr = mailSession.getTransport();
        } catch (NoSuchProviderException e) {
            log.error(e.getStackTrace());
        }
        log.info("MailSender is initialized");
    }

    public void send(MailMessage mailMessage) {
        MimeMessage message = new MimeMessage(mailSession);
        try {
            message.setFrom(new InternetAddress(myMail));
            if(mailMessage.getAddresses().isEmpty()){
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailMessage.getAddress()));
            } else {
                for(String address : mailMessage.getAddresses()){
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
                }
            }
            message.setSubject(mailMessage.getSubject());
            message.setText(mailMessage.getText());
            log.info("Email message is created for " + mailMessage.getAddress());
            tr.connect(myMail, pass);
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
            log.info("Sending mail to " + mailMessage.getAddress() + " - SUCCESS");
        } catch (MessagingException e) {
            log.error(e.getStackTrace());
            e.printStackTrace();
        }
    }

    public void sentNotification(Ticket ticket, State oldState){
        String link = host + "/overview/" + ticket.getId();
        MailMessage message = null;

        // template 1
        if((oldState.equals(State.DRAFT) || oldState.equals(State.DECLINED)) && ticket.getState().equals(State.NEW)){
            List<User> users = userService.getUsersByRole(Role.MANAGER);
            String text = "Dear Managers,\n"
                    + "\n"
                    + "New ticket " + link + " is waiting for your approval.";
            message = new MailMessage(users, "New ticket for approval", text);
        }

        // template 2
        if(oldState.equals(State.NEW) && ticket.getState().equals(State.APPROVED)){
            List<User> users = userService.getUsersByRole(Role.ENGINEER);
            users.add(ticket.getOwner());
            String text = "Dear Users,\n"
                    + "\n"
                    + "Ticket " + link + " was approved by the Manager.";
            message = new MailMessage(users, "Ticket was approved", text);
        }

        // template 3
        if(oldState.equals(State.NEW) && ticket.getState().equals(State.DECLINED)){
            String text = "Dear " + ticket.getOwner().getFirstName() + " "+ ticket.getOwner().getLastName() + ",\n"
                    + "\n"
                    + "Ticket " + link + " was declined by the Manager.\n";
            message = new MailMessage(ticket.getOwner(), "Ticket was declined", text);
        }

        // template 4
        if(oldState.equals(State.NEW) && ticket.getState().equals(State.CANCELED)){
            String text = "Dear " + ticket.getOwner().getFirstName() + " "+ ticket.getOwner().getLastName() + ",\n"
                    + "\n"
                    + "Ticket " + link + " was cancelled by the Manager.";
            message = new MailMessage(ticket.getOwner(), "Ticket was cancelled", text);
        }

        // template 5
        if(oldState.equals(State.APPROVED) && ticket.getState().equals(State.CANCELED)){
            List<User> users = new ArrayList<>();
            users.add(ticket.getOwner());
            users.add(ticket.getApprover());
            String text = "Dear Users,\n"
                    + "\n"
                    + "Ticket " + link + " was cancelled by the Engineer.";
            message = new MailMessage(users, "Ticket was cancelled", text);
        }

        // template 6
        if(oldState.equals(State.NEW) && ticket.getState().equals(State.CANCELED)){
            String text = "Dear " + ticket.getOwner().getFirstName() + " "+ ticket.getOwner().getLastName() + ",\n"
                    + "\n"
                    + "Ticket " + link + " was done by the Engineer.\n"
                    + "Please provide your feedback clicking on the ticket ID.";
            message = new MailMessage(ticket.getOwner(), "Ticket was done", text);
        }

        if(message != null){
            send(message);
        }
    }

    public void sentFeedBackNotification(Ticket ticket){
        String link = host + "/overview/" + ticket.getId();
        String text = "Dear " + ticket.getAssignee().getFirstName() + " "+ ticket.getAssignee().getLastName() + ",\n"
                + "\n"
                + "The feedback was provided on ticket " + link;
        MailMessage message = new MailMessage(ticket.getAssignee(), "Feedback was provided", text);
        send(message);
    }

}
