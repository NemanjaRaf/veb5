package rs.raf.demo.repositories.comment;

import rs.raf.demo.entities.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryCommentRepository implements CommentRepository {
    private List<Comment> comments = new CopyOnWriteArrayList<>();

    @Override
    public synchronized Comment addComment(Comment comment) {
        comment.setId(comments.size());
        comments.add(comment);
        return comment;
    }

    @Override
    public List<Comment> findAllComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public Comment findCommentById(Integer id) {
        return comments.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public synchronized void deleteComment(Integer id) {
        comments.removeIf(c -> c.getId().equals(id));
    }
}
