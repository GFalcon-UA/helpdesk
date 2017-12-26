package com.javamog.potapov.utils;

import com.javamog.potapov.model.Attachment;
import com.javamog.potapov.model.FileBucket;
import com.javamog.potapov.model.Ticket;
import org.springframework.web.multipart.MultipartFile;

public class AttachmentUtils {

    public static Attachment setAttachment(/*Ticket ticket, */MultipartFile multipartFile){

        Attachment attachment = new Attachment();
        attachment.setAttachmentName(multipartFile.getOriginalFilename());

        try {
            attachment.setContent(multipartFile.getBytes());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        //attachment.setAttachmentTicket(ticket);

        return attachment;
    }



}
