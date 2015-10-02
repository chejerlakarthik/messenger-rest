/**
 * 
 */
package org.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.javabrains.messenger.model.Comment;
import org.javabrains.messenger.model.ErrorMessage;
import org.javabrains.messenger.service.CommentService;

/**
 * @author 539471
 *
 */
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {
	
	CommentService commentService = new CommentService();
	
	@GET
	public List<Comment> getAllCommentsForMessage(@PathParam("messageId")long messageId){
		return commentService.getAllComments(messageId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getCommentById(@PathParam("messageId") long messageId, @PathParam("commentId")long commentId){
		return commentService.getCommentById(messageId, commentId);
	}
	
	@POST
	public Comment addNewComment(@PathParam("messageId")long messageId, Comment comment){
		return commentService.addNewComment(messageId, comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public Comment deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId")long commentId){
		return commentService.deleteComment(messageId, commentId);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId, Comment comment){
		comment.setCommentId(commentId);
		Comment updateComment = commentService.updateComment(messageId, comment);
		
		Response response = Response.status(Status.NOT_FOUND)
									.entity(new ErrorMessage(404,"Not found for Update","http://javabrains.koughik.org"))
									.build();
		
		if(null == updateComment){
			throw new NotFoundException(response);
		}
		return updateComment;
	}
}
