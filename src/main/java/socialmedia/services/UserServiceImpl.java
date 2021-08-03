package socialmedia.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import socialmedia.models.Message;
import socialmedia.models.User;
import socialmedia.repositories.MessageRepository;
import socialmedia.repositories.UserRepository;

/**
 * 
 * @author matt
 *
 *This class, known within SPing applications as a "Service" class, is responsible for housing the business logic of the application
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
	
	public List<Message> getUserTimelineByUserName(String userName) {
		User tempUser = userRepo.findByUserName(userName);
		return messageRepo.findAllByUser(tempUser);
	}
	
	public Message postMessageToTimelineByUsername(String userName, Message message) {
		User tempUser = userRepo.findByUserName(userName);
		message.setUser(tempUser);
		messageRepo.save(message);
		tempUser.postMessage(message);
		return message;
	}
}
