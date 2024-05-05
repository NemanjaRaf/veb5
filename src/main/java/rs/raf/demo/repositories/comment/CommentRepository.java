package rs.raf.demo.repositories.comment;

import rs.raf.demo.entities.Comment;

import java.util.List;

public interface CommentRepository {
    Comment addComment(Comment comment);
    List<Comment> findAllComments();
    Comment findCommentById(Integer id);
    void deleteComment(Integer id);
}
