package socialmedia.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import socialmedia.models.Message;
import socialmedia.models.User;
import socialmedia.repositories.UserRepository;

@Service
public class UserServiceImpl {
	@Autowired
	private UserRepository userRepo;
	
	public List<Message> getUserTimelineByUserName(String userName) {
		User tempUser = userRepo.findByUserName(userName);
		return tempUser.getTimeline();
	}
}
