package org.javabrains.messenger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comment {
	
	private long commentId;
	private String commentText;
	private String commentAuthor;
	private Date createdDate;
	
	public Comment(){
		this.commentId = 1;
		this.commentText = "Default Comment";
		this.commentAuthor = "Karthik";
		this.createdDate = new Date();
	}
	
	public Comment(long commentId, String commentText, String commentAuthor,
			Date createdDate) {
		this.commentId = commentId;
		this.commentText = commentText;
		this.commentAuthor = commentAuthor;
		this.createdDate = createdDate;
	}
	
	/**
	 * @return the commentId
	 */
	public long getCommentId() {
		return commentId;
	}
	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}
	/**
	 * @param commentText the commentText to set
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	/**
	 * @return the commentAuthor
	 */
	public String getCommentAuthor() {
		return commentAuthor;
	}
	/**
	 * @param commentAuthor the commentAuthor to set
	 */
	public void setCommentAuthor(String commentAuthor) {
		this.commentAuthor = commentAuthor;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
