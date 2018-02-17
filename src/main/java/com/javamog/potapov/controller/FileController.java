package com.javamog.potapov.controller;

import com.javamog.potapov.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(@RequestParam(value = "nTicketId") Long ticketId,
            @RequestParam(value = "nUserId") Long userId,
            @RequestParam(value = "sFileName", required = false) String fileName,
            @RequestBody byte[] file){

        System.out.println("ok");
        fileService.addAttachment(ticketId, file, fileName, userId);

    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public byte[] download(@RequestParam(value = "nAttachmentId") Long attachmentId){

        return fileService.getAttachmentById(attachmentId);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public void remove(@RequestParam(value = "nAttachmentId") Long attachmentId, @RequestParam(value = "nUserId") Long userId){
        fileService.removeAttachmentById(attachmentId, userId);
    }
}
