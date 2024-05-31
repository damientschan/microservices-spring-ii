package ch.hearc.jee24.postsservice.repository;

import ch.hearc.jee24.postsservice.service.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
