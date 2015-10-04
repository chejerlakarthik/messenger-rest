package org.javabrains.messenger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
	
	private long messageId;
	private String messageContent;
	private String author;
	private Date createdDate;
	private Map<Long,Comment> comments = new HashMap<Long,Comment>();
	private List<Link> links = new ArrayList<Link>();
	
	public Message(){
	}
	
	/**
	 * @param messageId
	 * @param messageContent
	 * @param author
	 * @param createdDate
	 * @param comments
	 */
	public Message(long messageId, String messageContent, String author) {
		this.messageId = messageId;
		this.messageContent = messageContent;
		this.author = author;
		this.createdDate = new Date();
		this.comments = new HashMap<Long,Comment>();
		
		Comment defaultComment = new Comment();
		this.comments.put(defaultComment.getCommentId(), defaultComment);
	}
	
	/**
	 * @return the messageId
	 */
	public long getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}
	/**
	 * @return the messageContent
	 */
	public String getMessageContent() {
		return messageContent;
	}
	/**
	 * @param messageContent the messageContent to set
	 */
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
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

	/**
	 * @return the comments
	 */
	public Map<Long, Comment> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments;
	}
	
	/**
	 * @return the links
	 */
	public List<Link> getLinks() {
		return links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(List<Link> links) {
		this.links = links;
	}

	/**
	 * Convenience method to add links to the Message
	 * 
	 * @param uri
	 * @param rel
	 */
	public void addLink(String uri, String rel){
		Link link = new Link();
		link.setLink(uri);
		link.setRel(rel);
		links.add(link);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", messageContent="
				+ messageContent + ", author=" + author + ", createdDate="
				+ createdDate + ", comments=" + comments + ", links=" + links
				+ "]";
	}
}
