package org.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.javabrains.messenger.model.Database;
import org.javabrains.messenger.model.Message;

public class MessageService {
	
	private Map<Long,Message> messages = Database.getMessages();
	
	public MessageService(){
		messages.put(1L, new Message(1L, "Hello World!", "Chejerla Karthik"));
		messages.put(2L, new Message(2L, "Hello India!", "Kaushik"));
	}
	
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messagesForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values())
		{
			cal.setTime(message.getCreatedDate());
			if(cal.get(Calendar.YEAR) == year){
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size){
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if (start + size > list.size()) return new ArrayList<Message>();
		return list.subList(start, start + size);
	}
	
	public Message getMessage(Long messageId){
		return messages.get(messageId);
	}
	
	public Message removeMessage(Long messageId){
		return messages.remove(messageId);
		
	}
	
	public Message addMessage(Message message){
		message.setMessageId(messages.size() + 1);
		messages.put(message.getMessageId(), message);
		return messages.get(message.getMessageId());
	}
	
	public Message updateMessage(Message message){
		if(message.getMessageId() <= 0){
			return null;
		}
		else{
			messages.put(message.getMessageId(), message);
			return messages.get(message.getMessageId());
		}
	}
}
