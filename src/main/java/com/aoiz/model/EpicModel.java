package com.aoiz.model;

public class EpicModel {
	private String email;
	private String key;

	public EpicModel(String email, String key) {
		this.email = email;
		this.key = key;
	}
	public EpicModel(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
