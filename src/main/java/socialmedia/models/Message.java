package socialmedia.models;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "message_user_id")
	private User user;
	
	@ManyToMany(mappedBy = "likedMessages", cascade = CascadeType.ALL)
	private Set<User> likes;
	
	private String message;
	
	@JsonFormat(pattern="hh:mm dd/MM")
	private LocalDateTime date;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "message")
	private List<Comment> comments;
	
	public Message() {
		this.setDate(LocalDateTime.now());
	}
	
	public Message(String message) {
		this.setMessage(message);
		this.setDate(LocalDateTime.now());
	}
	
	public Message(String message, User user) {
		this.setMessage(message);
		this.user = user;
		this.setDate(LocalDateTime.now());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void setLikes(Set<User> likeUsers) {
		this.likes = likeUsers;
	}

}
