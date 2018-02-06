package com.javamog.potapov.dao.Impl;

import com.javamog.potapov.dao.CommentDAO;
import com.javamog.potapov.domain.Comment;
import com.javamog.potapov.parent.dao.GenericEntityDao;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImpl extends GenericEntityDao<Comment> implements CommentDAO {
    protected CommentDaoImpl() {
        super(Comment.class);
    }
}
