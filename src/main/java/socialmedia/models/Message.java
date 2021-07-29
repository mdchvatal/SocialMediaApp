package socialmedia.models;

import java.util.Date;

public class Message {
	private User user;
	private String message;
	private Date date;
	
	public Message(String message) {
		this.setMessage(message);
		this.setDate(new Date());
	}
	
	public Message(String message, User user) {
		this.setMessage(message);
		this.user = user;
		this.setDate(new Date());
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
