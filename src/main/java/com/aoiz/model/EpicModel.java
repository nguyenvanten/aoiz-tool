package com.aoiz.model;

import com.poiji.annotation.ExcelCellName;

public class EpicModel {
	@ExcelCellName("email")
	private String email;

	@ExcelCellName("Pass")
	private String pass;

	@ExcelCellName("keyNode")
	private String keyNode;

	public EpicModel() {
	}

	public EpicModel(String email, String keyNode) {
		this.email = email;
		this.keyNode = keyNode;
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getKeyNode() {
		return keyNode;
	}

	public void setKeyNode(String keyNode) {
		this.keyNode = keyNode;
	}

}
