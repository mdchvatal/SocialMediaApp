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
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/users/{userName}/timeline")
	public List<Message> getUserStory(@PathVariable String userName) {
		return userService.getUserTimelineByUserName(userName);
	}
	
}
