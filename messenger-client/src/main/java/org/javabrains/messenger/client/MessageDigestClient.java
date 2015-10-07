/**
 * 
 */
package org.javabrains.messenger.client;

import java.net.URI;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.javabrains.messenger.client.auth.ClientAuthenticationFilter;
import org.javabrains.messenger.model.Message;

/**
 * @author 539471
 *
 */
public class MessageDigestClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		MessageDigestClient mdClient = new MessageDigestClient();
		final URI BASE_URI = UriBuilder.fromUri("http://localhost:8080/messenger/webapi").build();  
		
		//Get all available messages
		mdClient.getAllMessages(BASE_URI);
	}
	
	private void getAllMessages(final URI BASE_URI) {
		GenericType<List<Message>> responseType = new GenericType<List<Message>>() {};
		
		Builder builder = getSSLRequestBuilder(BASE_URI);
		Invocation invocation = builder.buildGet();
		List<Message> messages = invocation.invoke(responseType);

		for (Message message : messages) {
			System.out.println(message.getMessageId() + " ---- "
								+ message.getAuthor() + " ---- "
								+ message.getMessageContent());
		}
	}

	private Builder getSSLRequestBuilder(final URI BASE_URI) {
		
		// Client Basic Auth set up
		ClientAuthenticationFilter filter = new ClientAuthenticationFilter();
		
		// Set up ssl configuration
		SslConfigurator sslConfig = SslConfigurator.newInstance()
				   .trustStoreFile("")
				   .trustStorePassword("")
				   .keyStoreFile("")
				   .keyPassword("");

		// create sslContext from configuration
		SSLContext sslContext = sslConfig.createSSLContext();

		// Set the SSL Context to the client
		Client client = ClientBuilder.newBuilder().sslContext(sslContext).build();

		return client.target(BASE_URI)
					 .path("messages")
					 .register(filter)
					 .request()
					 .property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_DIGEST_USERNAME,"ckarthik")
					 .property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_DIGEST_PASSWORD,"password");
	}
}
