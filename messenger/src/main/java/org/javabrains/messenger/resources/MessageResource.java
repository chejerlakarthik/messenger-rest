package org.javabrains.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.javabrains.messenger.exception.DataNotFoundException;
import org.javabrains.messenger.model.Message;
import org.javabrains.messenger.resources.beans.MessageFilterBean;
import org.javabrains.messenger.service.MessageService;

@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();

	@GET
	public List<Message> getAllMessages(@BeanParam MessageFilterBean filterBean) {
		if(filterBean.getYear() > 0){
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if(filterBean.getStart() > 0 && filterBean.getSize() > 0){
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") Long messageId) throws DataNotFoundException {
		Message message = messageService.getMessage(messageId);
		if(null == message){
			throw new DataNotFoundException("Cannot find a message with id:"+ messageId);
		}
		return message;
	}

	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") Long id) {
		messageService.removeMessage(id);
	}

	@POST
	public Response createMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = messageService.addMessage(message);
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newMessage.getMessageId())).build();
		return Response.created(uri)
						.entity(newMessage)
						.build();
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id,
			Message message) throws DataNotFoundException{
		message.setMessageId(id);
		Message updateMessage = messageService.updateMessage(message);
		if(null == updateMessage){
			throw new DataNotFoundException("Message with id "+ message.getMessageId() + " not found!! Update failed !!");
		}
		return updateMessage;
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
}
