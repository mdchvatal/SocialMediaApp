package socialmedia.models;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 
 * @author matt
 * 
 *Basic User concrete class. Acts as model for holding "Story" as a List of Message objects. The story will contain all the aggregated 
 *message objects from the user itslef. ALso contains "Timeline" - an aggregation of all the stories of users in "Followers" list and 
 *from story in user itself. 
 *
 *Can "Follow" other users (which adds user itself to followed user's list of followers).
 *
 *Whenever the user published a message to their story, all followers are automatically "updated", which pushes the message to the follower's 
 *timeline automatically.
 */
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "timeline_id", referencedColumnName = "timeline_id")
	private Timeline timeline;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<User> followers;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@JsonIgnore
	private List<Comment> comments;
	
	@ManyToMany
	@JoinTable(
			  name = "message_likes", 
			  joinColumns = @JoinColumn(name = "user_id"), 
			  inverseJoinColumns = @JoinColumn(name = "message_id"))
	@JsonIgnore
	private Set<Message> likedMessages;
	
	@Column(name = "userName")
	private String userName;
	
	public User() {
	}
	
	public User(String username) {
		this.userName = username;
	}
	
	public void postMessage(Message message) {
		this.getTimeline().addMessage(message);
		update(message);
	}
	
	public void addMessageToTimeline(Message message) {
		this.getTimeline().addMessage(message);
	}
	
	public void update(Message message) {
		for (User user : followers) {
			user.addMessageToTimeline(message);
		}
	}
	
	public void follow(User user) {
		user.addFollower(this);
	}
	
	public void addFollower(User user) {
		followers.add(user);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Set<User> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Set<Message> getLikedMessages() {
		return likedMessages;
	}

	public void setLikedMessages(Set<Message> likedMessages) {
		this.likedMessages = likedMessages;
	}
	
	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}
	
	public Timeline getTimeline() {
		return timeline;
	}
}
	