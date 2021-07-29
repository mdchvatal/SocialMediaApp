package socialmedia.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import socialmedia.models.Message;
import socialmedia.models.User;
import socialmedia.services.UserServiceImpl;

@RestController
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping("/users/{userName}/timeline")
	public ArrayList<Message> getUserStory(@PathVariable String user) {
		return userService.getUserTimelineByUserName(user);
	}
	
}
