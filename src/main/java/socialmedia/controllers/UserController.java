package socialmedia.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import socialmedia.models.Message;
import socialmedia.models.User;
import socialmedia.services.UserServiceImpl;

@RestController
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	/**Takes a user name as a path variable, returns a List (in Java) or array (when parsed to JSON on client side) of Message objects
	 * 
	 * @param userName
	 * @return
	 */
	@GetMapping("/users/{userName}/timeline")
	public Set<Message> getUserTimeline(@PathVariable String userName) {
		return userService.getUserTimelineByUserName(userName);
	}
	
	/**
	 * 
	 * @param userName
	 * @param message
	 * @return message
	 * 
	 * Takes user name from a path variable in the request url and a message object parsed from the request's JSON body. Looks up the user by username
	 * and adds the message object to the user's timeline. Returns the message as a response as a utility for the caller.
	 */
	@PostMapping("/users/{userName}/timeline")
	public Message postUserMessageToTimeline(@PathVariable String userName, @RequestBody Message message) {
		return userService.postMessageToTimelineByUsername(userName, message);
	}
	
	@PostMapping("/users/{userName}/follow")
	public User followUserByUsername(@PathVariable String userName, @RequestBody String userToFollow) {
		return userService.followUserByUserName(userName, userToFollow);
	}
	
}
