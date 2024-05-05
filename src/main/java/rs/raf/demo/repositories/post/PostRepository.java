package rs.raf.demo.repositories.post;

import rs.raf.demo.entities.Post;
import rs.raf.demo.entities.Comment;

import java.util.List;

public interface PostRepository {
    Post addPost(Post post);
    List<Post> allPosts();
    Post findPost(Integer id);
    void deletePost(Integer id);
    void addCommentToPost(Integer postId, Comment comment);
}
