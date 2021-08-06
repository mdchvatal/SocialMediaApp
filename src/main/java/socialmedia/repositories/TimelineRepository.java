package socialmedia.repositories;

import org.springframework.data.repository.CrudRepository;

import socialmedia.models.Timeline;

public interface TimelineRepository extends CrudRepository<Timeline, Integer> {
	
	public Timeline findByUserUserName(String userName);
}
