package socialmedia.models;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class User {
	private ArrayList<Message> timeline = new ArrayList<>();
	private ArrayList<User> followers = new ArrayList<>();
	private ArrayList<Message> userStory = new ArrayList<>();
	private String userName;
	
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
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ArrayList<Message> getTimeline() {
		return timeline;
	}
	public void setTimeline(ArrayList<Message> timeline) {
		this.timeline = timeline;
	}
	public ArrayList<User> getFollowers() {
		return followers;
	}
	public void setFollowers(ArrayList<User> followers) {
		this.followers = followers;
	}
	public ArrayList<Message> getUserStory() {
		return userStory;
	}
	public void setUserStory(ArrayList<Message> userStory) {
		this.userStory = userStory;
	}
	
	
}
