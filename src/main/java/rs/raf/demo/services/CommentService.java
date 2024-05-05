package rs.raf.demo.services;

import rs.raf.demo.entities.Comment;
import rs.raf.demo.repositories.comment.CommentRepository;

import javax.inject.Inject;
import java.util.List;

public class CommentService {

    @Inject
    private CommentRepository commentRepository;

    public Comment addComment(Comment comment) {

        return commentRepository.addComment(comment);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAllComments();
    }

    public Comment getComment(Integer id) {
        return commentRepository.findCommentById(id);
    }

    public void deleteComment(Integer id) {
        commentRepository.deleteComment(id);
    }
}