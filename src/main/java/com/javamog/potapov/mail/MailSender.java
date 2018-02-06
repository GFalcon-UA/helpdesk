package com.javamog.potapov.mail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
@Scope("session")
public class MailSender {

    private static final Logger log = LogManager.getLogger(MailSender.class);

    private static final String myMail = "wealth.is.goal@gmail.com";
    private static final String pass = "max3521267";

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
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailMessage.getSubject()));
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
}
