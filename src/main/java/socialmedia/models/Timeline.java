package socialmedia.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Timeline {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "timeline_id")
	private int id;
	
	@OneToOne(mappedBy="timeline")
	@JsonIgnore
	private User user;
	
	@ManyToMany
	@JoinTable(
			  name = "timeline_messages", 
			  joinColumns = @JoinColumn(name = "timeline_id"), 
			  inverseJoinColumns = @JoinColumn(name = "message_id"))
	@JsonIgnore
	private Set<Message> timelineMessages;
	
	public Timeline() {
	}
	
	public void addMessage(Message message) {
		timelineMessages.add(message);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Message> getTimelineMessages() {
		return timelineMessages;
	}

	public void setTimelineMessages(HashSet<Message> timelineMessages) {
		this.timelineMessages = timelineMessages;
	}
	
	
}
