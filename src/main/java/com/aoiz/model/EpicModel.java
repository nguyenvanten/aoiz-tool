package com.aoiz.model;

import com.poiji.annotation.ExcelCellName;

public class EpicModel {
	@ExcelCellName("email")
	private String email;

	@ExcelCellName("keyNode")
	private String keyNode;

	public EpicModel() {
	}

	public EpicModel(String email, String key) {
		this.email = email;
		this.keyNode = key;
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
		return keyNode;
	}

	public void setKey(String key) {
		this.keyNode = key;
	}

}
