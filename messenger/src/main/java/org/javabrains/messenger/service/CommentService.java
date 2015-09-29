package org.javabrains.messenger.service;

import java.util.List;
import java.util.Map;

import org.javabrains.messenger.model.Comment;
import org.javabrains.messenger.model.Database;
import org.javabrains.messenger.model.Message;

public class CommentService {
	
	private Map<Long,Message> messages = Database.getMessages();
	
	public List<Comment> getAllComments(long messageId){
		Message message = messages.get(messageId);
		return message.getListOfComments();
	}

	public Comment getCommentById(long messageId, long commentId){
		List<Comment> commentsForMessage = messages.get(messageId).getListOfComments();
		for(Comment comment: commentsForMessage){
			if(comment.getCommentId() == commentId){
				return comment;
			}
		}
		return null;
	}
	
	public Comment updateComment(Comment comment){
		return null;
	}
	
	public Comment addNewComment(Comment comment){
		return null;
	}
	
	public void deleteComment(long id){
		
	}
	
}
