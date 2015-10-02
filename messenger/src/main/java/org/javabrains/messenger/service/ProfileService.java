package org.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.javabrains.messenger.model.Database;
import org.javabrains.messenger.model.Profile;

public class ProfileService {

	private Map<String, Profile> profiles = Database.getProfiles();

	public ProfileService() {
		profiles.put("chejerla.karthik", new Profile("chejerla.karthik",
				"Karthik", "Chejerla"));
		profiles.put("koushik.kothagal", new Profile("koushik.kothagal", "Koushik",
				"Kothagal"));
	}

	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfileByName(String profileName) {
		return profiles.get(profileName);
	}

	public Profile updateProfile(Profile profile) {
		if (profiles.get(profile.getProfileName()) == null) {
			return null;
		}
		profile.setProfileId(profiles.get(profile.getProfileName()).getProfileId());
		profile.setCreatedDate(profiles.get(profile.getProfileName()).getCreatedDate());
		profiles.put(profile.getProfileName(), profile);
		return profiles.get(profile.getProfileName());
	}

	public Profile addProfile(Profile profile) {
		profile.setCreatedDate(new Date());
		profile.setProfileId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profiles.get(profile.getProfileName());
	}

	public void deleteProfile(String profileName) {
		profiles.remove(profileName);
	}
}
