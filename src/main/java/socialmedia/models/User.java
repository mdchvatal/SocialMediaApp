package socialmedia.models;
import java.text.SimpleDateFormat;
import java.util.List;

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
	@Column(name = "id")
	private Integer id;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@JsonIgnore
	private List<Message> timeline;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<User> followers;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@JsonIgnore
	private List<Message> userStory;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@JsonIgnore
	private List<Comment> comments;
	
	@ManyToMany
	@JoinTable(
			  name = "message_likes", 
			  joinColumns = @JoinColumn(name = "user_id"), 
			  inverseJoinColumns = @JoinColumn(name = "message_id"))
	@JsonIgnore
	private List<Message> likedMessages;
	
	@Column(name = "userName")
	private String userName;
	
	public User() {
	}
	
	public User(String username) {
		this.userName = username;
	}
	
	public void displayStory() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		System.out.println("----  Your Story ----");
		for (Message message : getUserStory()) {
			System.out.println(message.getMessage() + " -- " + formatter.format(message.getDate()));
		}
	}
	
	public void displayTimeline() {
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm MM-dd-yyyy");
		System.out.println("---- Your Timeline ----");
		for (Message message : getTimeline()) {
			System.out.println(message.getMessage() + " -- " + 
					message.getUser().getUserName() + " " + formatter.format(message.getDate()));
		}
	}
	
	public void postMessage(Message message) {
		userStory.add(message);
		timeline.add(message);
		this.update(message);
	}
	
	private void addMessageToTimeline(Message message) {
		this.timeline.add(message);
	}
	
	public void update(Message message) {
		for (User user : followers) {
			user.addMessageToTimeline(message);
		}
	}
	
	public void follow(User user) {
		user.addFollower(this);
	}
	
	private void addFollower(User user) {
		this.followers.add(user);
		
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

	public List<Message> getTimeline() {
		return timeline;
	}

	public void setTimeline(List<Message> timeline) {
		this.timeline = timeline;
	}

	public List<User> getFollowers() {
		return followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	public List<Message> getUserStory() {
		return userStory;
	}

	public void setUserStory(List<Message> userStory) {
		this.userStory = userStory;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Message> getLikedMessages() {
		return likedMessages;
	}

	public void setLikedMessages(List<Message> likedMessages) {
		this.likedMessages = likedMessages;
	}
}
	