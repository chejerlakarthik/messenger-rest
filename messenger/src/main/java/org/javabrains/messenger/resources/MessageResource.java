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
import org.javabrains.messenger.model.Profile;
import org.javabrains.messenger.resources.beans.MessageFilterBean;
import org.javabrains.messenger.service.MessageService;
import org.javabrains.messenger.service.ProfileService;

@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();
	
	/**
	 * Method to service the get request for all available message resources
	 * 
	 * @param filterBean
	 * @return
	 */
	@GET
	public List<Message> getAllMessages(@BeanParam MessageFilterBean filterBean, @Context UriInfo uriInfo ) {
		if(filterBean.getYear() > 0){
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if(filterBean.getStart() > 0 && filterBean.getSize() > 0){
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		List<Message> allMessages = messageService.getAllMessages();
		
		for(Message message : allMessages){
			message.addLink(getUriForSelf(message, uriInfo), "self");
			message.addLink(getUriForProfile(message,uriInfo),"profile");
			message.addLink(getUriForComments(message,uriInfo),"comments");
		}
		return allMessages;
	}
	
	/**
	 * Method to service the get requests for specific message resources
	 * 
	 * @param messageId
	 * @param uriInfo
	 * @return
	 * @throws DataNotFoundException
	 */
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") Long messageId, @Context UriInfo uriInfo) throws DataNotFoundException {
		Message message = messageService.getMessage(messageId);
		if(null == message){
			throw new DataNotFoundException("Cannot find a message with id:"+ messageId);
		}
		message.addLink(getUriForSelf(message, uriInfo), "self");
		message.addLink(getUriForProfile(message,uriInfo),"profile");
		message.addLink(getUriForComments(message,uriInfo),"comments");
		return message;
	}
	
	/**
	 * Method to service the requests for deleting message resources
	 * 
	 * @param id
	 */
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") Long id) {
		messageService.removeMessage(id);
	}
	
	/**
	 * Method to service the requests for creating new message resources
	 * 
	 * @param message
	 * @param uriInfo
	 * @return
	 */
	@POST
	public Response createMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = messageService.addMessage(message);
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newMessage.getMessageId())).build();
		return Response.created(uri)
						.entity(newMessage)
						.build();
	}
	
	/**
	 * Method to service the requests for updating the message resources
	 * 
	 * @param id
	 * @param message
	 * @return message
	 * @throws DataNotFoundException
	 */
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
	
	/**
	 * To get an instance of the Message sub-resource - CommentResource
	 * 
	 * @return
	 */
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
	
	/**
	 * Get Uri for self - to implement HATEOAS
	 * @param message
	 * @param uriInfo
	 * @return
	 */
	private String getUriForSelf(Message message, UriInfo uriInfo) {
		String uri = uriInfo.getBaseUriBuilder()
			   .path(MessageResource.class)
			   .path(String.valueOf(message.getMessageId()))
			   .build().toString();
		return uri;
	}
	
	/**
	 * Get Uri for Author profile - to implement HATEOAS
	 * @param message
	 * @param uriInfo
	 * @return
	 */
	private String getUriForProfile(Message message, UriInfo uriInfo) {
		String uri = "";
		String authorName = message.getAuthor();
		ProfileService profileService = new ProfileService();
		List<Profile> allProfiles = profileService.getAllProfiles();
		for (Profile profile : allProfiles) {
			String name = profile.getFirstName() + " " + profile.getLastName();
			if (name.equalsIgnoreCase(authorName)) {
				uri = uriInfo.getBaseUriBuilder()
						.path(ProfileResource.class)
						.path(profile.getProfileName())
						.build().toString();
			}
		}
		return uri;
	}
	
	/**
	 * Get Uri for Message Comments - to implement HATEOAS
	 * 
	 * @param message
	 * @param uriInfo
	 * @return
	 */
	private String getUriForComments(Message message, UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder()
							.path(MessageResource.class)
				            .path(MessageResource.class, "getCommentResource")		// this is to pick up method level Path annotation values
				            .path(CommentResource.class)
				            .resolveTemplate("messageId", message.getMessageId())	// this is to substitute the variable field in path with value
				            .build().toString();
	}
}
