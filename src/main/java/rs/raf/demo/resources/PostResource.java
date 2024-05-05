package rs.raf.demo.resources;

import rs.raf.demo.entities.Post;
import rs.raf.demo.entities.Comment;
import rs.raf.demo.services.PostService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/posts")
public class PostResource {

    @Inject
    private PostService postService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response allPosts() {
        return Response.ok(this.postService.allPosts()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Post createPost(@Valid Post post) {
        return this.postService.addPost(post);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Post getPost(@PathParam("id") Integer id) {
        return this.postService.findPost(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deletePost(@PathParam("id") Integer id) {
        this.postService.deletePost(id);
        return Response.noContent().build();
    }

    @POST
    @Path("/{postId}/comments")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addComment(@PathParam("postId") Integer postId, @Valid Comment comment) {
        this.postService.addCommentToPost(postId, comment);
        return Response.status(Response.Status.CREATED).entity(comment).build();
    }
}
