package socialmedia.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import socialmedia.models.Message;
import socialmedia.models.Timeline;
import socialmedia.models.User;
import socialmedia.repositories.MessageRepository;
import socialmedia.repositories.TimelineRepository;
import socialmedia.repositories.UserRepository;

/**
 * 
 * @author matt
 *
 *This class, known within Spring applications as a "Service" class, is responsible for housing the business logic of the application
 *as it pertains to Users and their exposed APIs. Any needed repository is injected into the class as an instance variable created
 *by the Spring Boot application through the "Autowired" annotation. Repositories are all interfaces. Spring Boot surmises a concrete 
 *implementation and implements it automatically. 
 *
 */

@Service
public class UserServiceImpl {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MessageRepository messageRepo;
	
	@Autowired
	private TimelineRepository timelineRepo;
	
	
	public Set<Message> getUserTimelineByUserName(String userName) {
		return timelineRepo.findByUserUserName(userName).getTimelineMessages();
	}
	
	public Message postMessageToTimelineByUsername(String userName, Message message) {
		User tempUser = userRepo.findByUserName(userName);
		System.out.println(tempUser.getTimeline());
		message.setPostingUser(tempUser);
		message.addTimeline(tempUser.getTimeline());
		messageRepo.save(message);
		tempUser.postMessage(message);
		return message;
	}

	public User followUserByUserName(String userName, String userToFollow) {
		User tempUser = userRepo.findByUserName(userName);
		User tempUserToFollow = userRepo.findByUserName(userToFollow);
		userRepo.findByUserName(userName).follow(tempUserToFollow);
		for(Message message : tempUserToFollow.getTimeline().getTimelineMessages()) {
			tempUser.addMessageToTimeline(message);
			messageRepo.save(message);
		}
		return tempUserToFollow;
	}
	
}
