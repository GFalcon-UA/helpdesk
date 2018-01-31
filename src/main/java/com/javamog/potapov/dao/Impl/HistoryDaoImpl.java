package com.javamog.potapov.dao.Impl;

import com.javamog.potapov.dao.HistoryDAO;
import com.javamog.potapov.domain.History;
import com.javamog.potapov.parent.dao.GenericEntityDao;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryDaoImpl extends GenericEntityDao<History> implements HistoryDAO {
    protected HistoryDaoImpl() {
        super(History.class);
    }
}
