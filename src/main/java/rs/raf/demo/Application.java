package rs.raf.demo;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import rs.raf.demo.services.PostService;
import rs.raf.demo.services.CommentService;
import rs.raf.demo.repositories.post.InMemoryPostRepository;
import rs.raf.demo.repositories.post.PostRepository;
import rs.raf.demo.repositories.comment.InMemoryCommentRepository;
import rs.raf.demo.repositories.comment.CommentRepository;
import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import java.util.logging.Logger;

@ApplicationPath("/api")
public class Application extends ResourceConfig {
    public Application() {
        Logger logger = Logger.getLogger(Application.class.getName());
        logger.severe("Application started *************************************************************************");
        packages("rs.raf.demo.resources");

        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                bind(InMemoryPostRepository.class).to(PostRepository.class).in(Singleton.class);
                bind(InMemoryCommentRepository.class).to(CommentRepository.class).in(Singleton.class);
                bind(PostService.class).to(PostService.class);
                bind(CommentService.class).to(CommentService.class);
            }
        };

        register(binder);
    }
}
