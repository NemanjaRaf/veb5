package rs.raf.demo.repositories.post;

import rs.raf.demo.entities.Post;
import rs.raf.demo.entities.Comment;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

public class InMemoryPostRepository implements PostRepository {
    private static List<Post> posts = new CopyOnWriteArrayList<>();

    public synchronized Post addPost(Post post) {
        Integer id = posts.size();
        post.setId(id);
        posts.add(post);
        return post;
    }

    public List<Post> allPosts() {
        return new ArrayList<>(posts);
    }

    public Post findPost(Integer id) {
        return posts.get(id);
    }

    public synchronized void deletePost(Integer id) {
        posts.remove(id.intValue());
    }

    private static final Logger logger = Logger.getLogger(InMemoryPostRepository.class.getName());

    public synchronized void addCommentToPost(Integer postId, Comment comment) {
        Post post = findPost(postId);
        if (post != null) {
            logger.info("Adding comment to post: " + postId);
            List<Comment> comments = post.getComments();
            comment.setId(comments.size());
            comments.add(comment);
            logger.info("Comment added. Total comments now: " + comments.size());
        } else {
            logger.warning("Post not found: " + postId);
        }
    }

}