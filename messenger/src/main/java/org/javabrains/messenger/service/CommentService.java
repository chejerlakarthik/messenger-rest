package org.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.javabrains.messenger.model.Comment;
import org.javabrains.messenger.model.Database;
import org.javabrains.messenger.model.Message;

public class CommentService {
	
	private Map<Long,Message> messages = Database.getMessages();
	
	public List<Comment> getAllComments(long messageId){
		Message message = messages.get(messageId);
		return new ArrayList<Comment>(message.getComments().values());
	}

	public Comment getCommentById(long messageId, long commentId){
		Comment comment = messages.get(messageId).getComments().get(commentId);
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment){
		Map<Long,Comment> commentsForMessage = messages.get(messageId).getComments();
		boolean commentExists = commentsForMessage.containsKey(comment.getCommentId());
		
		if(commentExists){
			commentsForMessage.put(comment.getCommentId(), comment);
		}
		return commentsForMessage.get(comment.getCommentId());
	}
	
	public Comment addNewComment(long messageId,Comment comment){
		Message message = messages.get(messageId);
		comment.setCommentId(message.getComments().size()+1);
		message.getComments().put(comment.getCommentId(), comment);
		return message.getComments().get(comment.getCommentId());
	}
	
	public Comment deleteComment(long messageId, long commentId){
		return messages.get(messageId).getComments().remove(commentId);
	}
	
}
