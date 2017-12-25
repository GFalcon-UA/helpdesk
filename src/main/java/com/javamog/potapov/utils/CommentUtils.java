package com.javamog.potapov.utils;

import com.javamog.potapov.model.Comment;
import com.javamog.potapov.model.Ticket;
import com.javamog.potapov.model.User;

import java.util.Date;

public class CommentUtils {

    public static Comment addComment(User user, Ticket ticket, String text) {

        Comment comment = new Comment();

        comment.setCommentUser(user);
        //comment.setCommentTicket(ticket);
        comment.setText(text);
        comment.setCommentDate(new Date());

        return comment;
    }
}
