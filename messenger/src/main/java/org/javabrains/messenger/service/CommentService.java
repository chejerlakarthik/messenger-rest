package org.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.javabrains.messenger.model.Comment;
import org.javabrains.messenger.model.Database;
import org.javabrains.messenger.model.Message;

public class CommentService {
	
	private Map<Long,Message> messages = Database.getMessages();
	
	/**
	 * This method is used to return all the comments available for a given
	 * message id.
	 * 
	 * @param messageId
	 * @return
	 */
	public List<Comment> getAllComments(long messageId){
		Message message = messages.get(messageId);
		return new ArrayList<Comment>(message.getComments().values());
	}
	
	/**
	 * This method is used to retrieve a comment for a given message by using
	 * the comment id.
	 * 
	 * @param messageId
	 * @param commentId
	 * @return
	 */
	public Comment getCommentById(long messageId, long commentId){
		Comment comment = messages.get(messageId).getComments().get(commentId);
		return comment;
	}
	
	/**
	 * This method is used to update the comments for a given message id. The
	 * createdDate field will not be changed and will point to the original date.
	 * 
	 * @param messageId
	 * @param comment
	 * @return
	 */
	public Comment updateComment(long messageId, Comment comment){
		Map<Long,Comment> commentsForMessage = messages.get(messageId).getComments();
		boolean commentExists = commentsForMessage.containsKey(comment.getCommentId());
		Comment commentInMessage = commentsForMessage.get(comment.getCommentId());
		
		if(commentExists){
			// If the comment already exists; retain the comment created date
			comment.setCreatedDate(commentInMessage.getCreatedDate());
			commentsForMessage.put(comment.getCommentId(), comment);
		}
		return commentsForMessage.get(comment.getCommentId());
	}
	
	/**
	 * This method is used to add a new comment for a given message. The comment
	 * id is one plus the number of comments before inserting the comment.
	 * 
	 * @param messageId
	 * @param comment
	 * @return
	 */
	public Comment addNewComment(long messageId,Comment comment){
		Message message = messages.get(messageId);
		comment.setCommentId(message.getComments().size()+1);
		message.getComments().put(comment.getCommentId(), comment);
		return message.getComments().get(comment.getCommentId());
	}
	
	/**
	 * This method is used to remove a comment for a given message. It returns
	 * the comment which has been removed.
	 * 
	 * @param messageId
	 * @param commentId
	 * @return
	 */
	public Comment deleteComment(long messageId, long commentId){
		return messages.get(messageId).getComments().remove(commentId);
	}
	
}
