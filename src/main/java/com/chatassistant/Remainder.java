package com.chatassistant;
import java.time.LocalDateTime;

public class Remainder {
	
	private int id;
    private String userEmail;
    private String message;
    private LocalDateTime reminderTime;
    
    
	public Remainder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Remainder(int id, String userEmail, String message, LocalDateTime reminderTime) {
		super();
		this.id = id;
		this.userEmail = userEmail;
		this.message = message;
		this.reminderTime = reminderTime;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getReminderTime() {
		return reminderTime;
	}
	public void setReminderTime(LocalDateTime reminderTime) {
		this.reminderTime = reminderTime;
	}
    

}
