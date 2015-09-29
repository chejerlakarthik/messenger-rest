package org.javabrains.messenger.model;

import java.util.Date;

public class Profile {
	
	private long profileId;
	private String profileName;
	private String firstName;
	private String lastName;
	private Date createdDate;
	
	public Profile(){
		//no-arg constructor
	}
	
	public Profile(String profileName, String firstName, String lastName){
		this.profileName = profileName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.createdDate = new Date();
	}
	
	/**
	 * @return the profileId
	 */
	public long getProfileId() {
		return profileId;
	}
	/**
	 * @param profileId the profileId to set
	 */
	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}
	/**
	 * @return the profileName
	 */
	public String getProfileName() {
		return profileName;
	}
	/**
	 * @param profileName the profileName to set
	 */
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Profile [profileId=" + profileId + ", profileName="
				+ profileName + ", firstName=" + firstName + ", lastName="
				+ lastName + ", createdDate=" + createdDate + "]";
	}
}
