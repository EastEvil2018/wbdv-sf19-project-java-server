package com.example.wbdvsf19projectserverjava.models;

public class Message {
    private String message;

    public Message(String message) {
        this.message = message;
    }

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
    }
    
    public void set(String message) {
        this.message = message;
    }

}