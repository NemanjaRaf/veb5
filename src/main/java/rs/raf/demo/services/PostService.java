package rs.raf.demo.services;

import rs.raf.demo.entities.Post;
import rs.raf.demo.entities.Comment;
import rs.raf.demo.repositories.post.PostRepository;

import javax.inject.Inject;
import java.util.List;

public class PostService {

    @Inject
    private PostRepository postRepository;

    public PostService() {
        System.out.println(this);
    }

    public Post addPost(Post post) {
        return this.postRepository.addPost(post);
    }

    public List<Post> allPosts() {
        return this.postRepository.allPosts();
    }

    public Post findPost(Integer id) {
        return this.postRepository.findPost(id);
    }

    public void deletePost(Integer id) {
        this.postRepository.deletePost(id);
    }

    public void addCommentToPost(Integer postId, Comment comment) {
        this.postRepository.addCommentToPost(postId, comment);
    }

}
