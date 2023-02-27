package model;

import java.util.ArrayList;

public class User {
	private String username;
	private String password;
	private String first_name;
	private String last_name;
	private  ArrayList<Records> records;

	public User() {
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;

	}

	public ArrayList<Records> getRecords() {
		return records;
	}

	public User(String username, String password, String first_name, String last_name, ArrayList<Records> records) {
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.records = records;
	}

	public void setRecords(ArrayList<Records> records) {
		this.records = records;
	}

	public User(String first_name, String last_name, String username, String password) {
		this.username = username;
		this.password = password;
		this.first_name=first_name;
		this.last_name=last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
