package com.javamog.potapov.dao;

import com.javamog.potapov.model.Comment;

public interface CommentDao {

    void saveComment(Comment comment);

    void evictComment(Comment comment);
}
