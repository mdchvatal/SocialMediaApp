package socialmedia.repositories;

import org.springframework.data.repository.CrudRepository;

import socialmedia.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByUserName(String user);

}
