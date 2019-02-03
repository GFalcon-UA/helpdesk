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

package ua.com.gfalcon.helpdesk.controller;

import ua.com.gfalcon.helpdesk.json.JsonRestUtils;
import ua.com.gfalcon.helpdesk.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("api/file")
public class FileController {

    @Autowired
    private FileService fileService;


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity upload(
            @RequestParam("nTicketId") Long ticketId,
            @RequestParam("nUserId") Long userId,
            @RequestParam("sFileName") String name,
            @RequestParam("sFileType") String type,
            @RequestBody byte[] file
    ) {

        Long attachmentId = fileService.addAttachment(ticketId, userId, name, type, file);

        return JsonRestUtils.toJsonResponse(attachmentId);
    }


    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity download(@RequestParam(value = "nAttachmentId") Long attachmentId) {

        //byte[] encode = Base64.getEncoder().encode(fileService.getAttachmentById(attachmentId));

        return JsonRestUtils.toJsonResponse(fileService.getAttachmentById(attachmentId));
    }


    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public void remove(@RequestParam(value = "nAttachmentId") Long attachmentId, @RequestParam(value = "nUserId") Long userId) {
        fileService.removeAttachmentById(attachmentId, userId);
    }

}
