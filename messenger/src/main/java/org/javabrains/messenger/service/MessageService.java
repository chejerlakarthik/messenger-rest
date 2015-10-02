package org.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.javabrains.messenger.model.Database;
import org.javabrains.messenger.model.Message;
/**
 * 
 * @author Yashoda
 *
 */
public class MessageService {
	
	private Map<Long,Message> messages = Database.getMessages();
	
	/**
	 * no-arg constructor for MessageService
	 */
	public MessageService(){
		messages.put(1L, new Message(1L, "Hello World!", "Karthik Chejerla"));
		messages.put(2L, new Message(2L, "Hello India!", "Koushik Kothagal"));
	}
	
	/**
	 * Method to get all available messages
	 * 
	 * @return List<Message>
	 */
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	/**
	 * Method to get all available messages for a given year
	 * 
	 * @param year
	 * @return List<Message>
	 */
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messagesForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values()){
			cal.setTime(message.getCreatedDate());
			if(cal.get(Calendar.YEAR) == year){
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	/**
	 * Method to get all messages paginated starting from <tt>start</tt> &
	 * number of messages equal to <tt>size</tt>
	 * 
	 * @param start
	 * @param size
	 * @return List<Message>
	 */
	public List<Message> getAllMessagesPaginated(int start, int size){
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if (start + size > list.size()) return new ArrayList<Message>();
		return list.subList(start, start + size);
	}
	
	/**
	 * Method to get a message using messageId
	 * 
	 * @param messageId
	 * @return
	 */
	public Message getMessage(Long messageId){
		return messages.get(messageId);
	}
	
	/**
	 * Method to remove a message by using messageId
	 * 
	 * @param messageId
	 * @return
	 */
	public Message removeMessage(Long messageId){
		return messages.remove(messageId);
		
	}
	
	/**
	 * Method to add a new message. The messageId is computed by calculating the
	 * size of the list and then incrementing it by one.
	 * 
	 * @param message
	 * @return
	 */
	public Message addMessage(Message message){
		message.setMessageId(messages.size() + 1);
		messages.put(message.getMessageId(), message);
		return messages.get(message.getMessageId());
	}
	
	/**
	 * 
	 * @param message
	 * @return
	 */
	public Message updateMessage(Message message) {
		if(!messages.containsKey(message.getMessageId())){
			return null;
		}
		else{
			messages.put(message.getMessageId(), message);
			return messages.get(message.getMessageId());
		}
	}
}
