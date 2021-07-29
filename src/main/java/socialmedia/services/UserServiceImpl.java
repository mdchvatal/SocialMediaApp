package socialmedia.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import socialmedia.models.Message;
import socialmedia.models.User;
import socialmedia.repositories.UserRepository;

public class UserServiceImpl {
	@Autowired
	private UserRepository userRepo;
	
	public ArrayList<Message> getUserTimelineByUserName(String userName) {
		User tempUser = userRepo.findByUserName(userName);
		return tempUser.getTimeline();
	}
}
