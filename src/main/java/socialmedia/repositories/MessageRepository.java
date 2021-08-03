package socialmedia.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import socialmedia.models.Message;
import socialmedia.models.User;

public interface MessageRepository extends CrudRepository<Message, Integer> {
	
	public List<Message> findAllByUser(User user);
	
	public List<Message> findAllByUserUserName(String userName);
}
