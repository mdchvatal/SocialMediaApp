package socialmedia.models;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author matt
 * 
 * Concrete Message class. Is the primary class for holding typed data a user wishes to publish to their story and, if they have followers, 
 * to their followers. 
 * 
 * Aside from their primary purpose of holding typed messsages, Message objects keep track of the user who produced it, the date it 
 * was produced, and a List of "Comments"
 * 
 * See "Comment" class for details.
 *
 */

@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "message_id")
	private Integer id;
	
	@ManyToOne
	private User postingUser;
	
	@ManyToMany(mappedBy = "timelineMessages")
	@JsonIgnore
	private Set<Timeline> timelines;
	
	@ManyToMany(mappedBy = "likedMessages", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<User> likes;
	
	private int numberOfLikes;
	
	private String message;
	
	@JsonFormat(pattern="hh:mm dd/MM")
	private LocalDateTime date;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "message")
	private List<Comment> comments;
	
	private int numberOfComments;
	
	public Message() {
		this.setDate(LocalDateTime.now());
		this.timelines = new HashSet<Timeline>();
	}
	
	public Message(String message) {
		this.setMessage(message);
		this.setDate(LocalDateTime.now());
	}
	
	public Message(String message, User user) {
		this.setMessage(message);
		this.postingUser = user;
		this.setDate(LocalDateTime.now());
	}
	
	public void addTimeline(Timeline timeline) {
		timelines.add(timeline);
	}

	public User getPostingUser() {
		return postingUser;
	}

	public void setPostingUser(User postingUser) {
		this.postingUser = postingUser;
	}

	public Set<Timeline> getTimeline() {
		return timelines;
	}

	public void setTimelines(Set<Timeline> timelines) {
		this.timelines = timelines;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getDate() {
		return this.date;
	}

	public void setDate(LocalDateTime localDateTime) {
		this.date = localDateTime;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Set<User> getLikes() {
		return likes;
	}

	public void setLikes(HashSet<User> likeUsers) {
		this.likes = likeUsers;
	}

	public int getNumberOfLikes() {
		if (this.getLikes() == null) {
			return 0;
		} else {
		return this.getLikes().size();
		}
	}

	public void setNumberOfLikes(int numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}

	public int getNumberOfComments() {
		if (this.getComments() == null) {
			return 0;
		} else
		return this.getComments().size();
	}

	public void setNumberOfComments(int numberOfComments) {
		this.numberOfComments = numberOfComments;
	}
	
	public void addComment(Comment comment) {
		this.comments.add(comment);
		this.setNumberOfComments(this.getNumberOfComments());
	}
	
	public void addLike(User user) {
		this.likes.add(user);
		this.setNumberOfLikes(getNumberOfLikes());
	}

}
