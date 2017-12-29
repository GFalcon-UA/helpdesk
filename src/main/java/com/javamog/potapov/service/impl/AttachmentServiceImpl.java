package com.javamog.potapov.service.impl;

import com.javamog.potapov.dao.AttachmentDao;
import com.javamog.potapov.dao.HistoryDao;
import com.javamog.potapov.model.Attachment;
import com.javamog.potapov.model.History;
import com.javamog.potapov.model.Ticket;
import com.javamog.potapov.model.User;
import com.javamog.potapov.service.AttachmentService;
import com.javamog.potapov.utils.HistoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentService {

    private static final String FILE_IS_DELETED = "File is deleted";

    @Autowired
    private AttachmentDao attachmentDao;

    @Autowired
    private HistoryDao historyDao;

    @Override
    public void deleteAttachment(Attachment attachment, User user, Ticket ticket) {
        attachmentDao.deleteAttachment(attachment);
        History attachmentHistory = HistoryUtils.addHistoryRecord(user, ticket, FILE_IS_DELETED,
                FILE_IS_DELETED + ": " + attachment.getAttachmentName());
        historyDao.saveHistory(attachmentHistory);
    }
}
