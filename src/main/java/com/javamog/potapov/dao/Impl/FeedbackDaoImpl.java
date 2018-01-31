package com.javamog.potapov.dao.Impl;

import com.javamog.potapov.dao.FeedbackDAO;
import com.javamog.potapov.domain.Feedback;
import com.javamog.potapov.parent.dao.GenericEntityDao;
import org.springframework.stereotype.Repository;

@Repository
public class FeedbackDaoImpl extends GenericEntityDao<Feedback> implements FeedbackDAO {
    protected FeedbackDaoImpl() {
        super(Feedback.class);
    }
}
