package com.aoiz.model;

import com.poiji.annotation.ExcelCellName;

public class EpicModel {
	@ExcelCellName("STT")
	private String index;
	
	@ExcelCellName("email")
	private String email;

	@ExcelCellName("Pass")
	private String pass;

	@ExcelCellName("keyNode")
	private String keyNode;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
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
