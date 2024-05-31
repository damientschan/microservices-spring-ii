package ch.hearc.jee24.postsservice.service;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post create(Post post);
    Optional<Post> get(Long id);
    List<Post> getAll();
    Post update(Long id, Post post);
    void delete(Long id);
}
