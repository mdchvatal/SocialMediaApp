package socialmedia.repositories;

import org.springframework.data.repository.CrudRepository;

import socialmedia.models.Comment;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

}
