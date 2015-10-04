/**
 * 
 */
package org.javabrains.messenger.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.javabrains.messenger.model.Message;

/**
 * @author Yashoda
 *
 */
public class JerseyRESTClient {

	/**
	 * @param args
	 * @throws URISyntaxException 
	 */
	public static void main(String[] args) throws URISyntaxException {
		
		JerseyRESTClient restClient = new JerseyRESTClient();
		final URI BASE_URI = new URI("http://localhost:8080/messenger/webapi");
		// Create a new message
		Message message = new Message(0, "message created from jax-rs client",
				"Karthik Chejerla");
		restClient.createMessage(BASE_URI, message);
		message = new Message(0, "another message created from jax-rs client",
				"Ravi Teja");
		restClient.createMessage(BASE_URI, message);

		// Get all available messages
		restClient.getAllMessages(BASE_URI);
		
		// Get message by messageId
		restClient.getMessageById(BASE_URI, 4);
		
		// Update a message by messageId
		Message updateMessage = new Message(3, "message created from jax-rs client - updated", "Karthik Chejerla");
		restClient.updateMessage(BASE_URI, updateMessage);
		
		// Delete a message by messageId
		restClient.deleteMessage(BASE_URI, 4);
	}	

	private void getAllMessages(final URI BASE_URI) {
		GenericType<List<Message>> responseType = new GenericType<List<Message>>() {
		};
		
		Builder builder = getRequestBuilder(BASE_URI);
		Invocation invocation = builder.buildGet();
		List<Message> messages = invocation.invoke(responseType);

		for (Message message : messages) {
			System.out.println(message.getMessageId() + " ---- " + message.getAuthor() + " ---- " + message.getMessageContent());
		}
	}

	private void getMessageById(final URI BASE_URI, long messageId) {
		GenericType<Message> responseType = new GenericType<Message>() {
		};
		Builder builder = getRequestBuilder(BASE_URI, String.valueOf(messageId));
		Message message = builder.buildGet().invoke(responseType);
		System.out.println(message);
	}
	
	private void createMessage(final URI BASE_URI, Message message){
		Builder builder = getRequestBuilder(BASE_URI);
		Response response = builder.header("Accept", MediaType.APPLICATION_JSON)
								   .post(Entity.entity(message, MediaType.APPLICATION_JSON));
		String location = response.getLocation().toString();
		System.out.println(location);
	}
	
	private void updateMessage(final URI BASE_URI, Message message){
		GenericType<Message> responseType = new GenericType<Message>() {
		};
		Builder builder = getRequestBuilder(BASE_URI, String.valueOf(message.getMessageId()));
		Message updatedMessage = builder.header("Accept", MediaType.APPLICATION_JSON)
										.put(Entity.entity(message, MediaType.APPLICATION_JSON), responseType);
		System.out.println(updatedMessage.getMessageContent());
	}
	
	private void deleteMessage(final URI BASE_URI, long messageId){
		Builder builder = getRequestBuilder(BASE_URI, String.valueOf(messageId));
		Response response = builder.header("Accept", "text/xml").delete();
		System.out.println("Response Content Type: " + response.getHeaders().toString());
		System.out.println("Delete Status: " + response.getStatus());
		System.out.println("Removed message: " + (Message)response.readEntity(Message.class));
	}
	
	private Builder getRequestBuilder(final URI BASE_URI) {
		Client client = ClientBuilder.newClient();
		return client.target(BASE_URI).path("messages")
				.request();
	}
	
	private Builder getRequestBuilder(final URI BASE_URI, String pathFragment) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("messages").path(pathFragment);
		System.out.println(target);
		return target.request();
	}
}
