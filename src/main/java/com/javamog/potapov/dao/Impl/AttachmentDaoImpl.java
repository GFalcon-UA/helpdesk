package com.javamog.potapov.dao.Impl;

import com.javamog.potapov.dao.AttachmentDAO;
import com.javamog.potapov.domain.Attachment;
import com.javamog.potapov.parent.dao.GenericEntityDao;
import org.springframework.stereotype.Repository;

@Repository
public class AttachmentDaoImpl extends GenericEntityDao<Attachment> implements AttachmentDAO {
    protected AttachmentDaoImpl() {
        super(Attachment.class);
    }
}
