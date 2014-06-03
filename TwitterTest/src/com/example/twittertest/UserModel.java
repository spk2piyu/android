package com.example.twittertest;

import java.util.ArrayList;

public class UserModel {

	private String displayName;
	private ArrayList<String> tList = new ArrayList<String>();
	
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public ArrayList<String> gettList() {
		return tList;
	}
	public void add(String msg) {
		tList.add(msg);
	}
}
