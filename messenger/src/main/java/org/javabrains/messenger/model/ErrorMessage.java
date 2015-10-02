package org.javabrains.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {

	private long errorCode;
	private String errorMessage;
	private String documentation;
	
	public ErrorMessage(){
		
	}
	
	/**
	 * @param errorCode
	 * @param errorMessage
	 * @param documentation
	 */
	public ErrorMessage(long errorCode, String errorMessage,
			String documentation) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.documentation = documentation;
	}
	
	/**
	 * @return the errorCode
	 */
	public long getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the documentation
	 */
	public String getDocumentation() {
		return documentation;
	}
	/**
	 * @param documentation the documentation to set
	 */
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
}
