package socialmedia.repositories;

import org.springframework.data.repository.CrudRepository;

import socialmedia.models.Message;

public interface MessageRepository extends CrudRepository<Message, Integer> {

}
