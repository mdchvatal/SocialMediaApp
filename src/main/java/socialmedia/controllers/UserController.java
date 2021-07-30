package socialmedia.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	//Takes a user name as a path variable, returns a List (in Java) or array (when parsed to JSON on client side) of Message objects
	@GetMapping("/users/{userName}/timeline")
	public List<Message> getUserStory(@PathVariable String userName) {
		return userService.getUserTimelineByUserName(userName);
	}
	
}
