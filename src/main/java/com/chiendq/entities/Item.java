package com.chiendq.entities;

public class Item {
	private String description;
	private int status;
	private int id;
	public Item() {
		
	}
	
	public Item(String description, int status) {
		this.description=description;
		this.status =status;
	}
	
	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
