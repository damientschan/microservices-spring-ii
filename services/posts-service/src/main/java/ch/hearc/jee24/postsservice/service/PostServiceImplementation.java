package ch.hearc.jee24.postsservice.service;

import ch.hearc.jee24.postsservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService {

    @Autowired
    PostRepository repository;

    @Override
    public Post create(Post post) {
        return repository.save(post);
    }

    @Override
    public Optional<Post> get(Long id) {
        Optional<Post> optionalPost;
        optionalPost = repository.findById(id);
        return optionalPost;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        repository.findAll().forEach(posts::add);
        return posts;
    }

    @Override
    public Post update(Long id, Post post) {
        if(repository.findById(id).isPresent()) {
            post.setId(id);
        }
        return repository.save(post);
    }

    @Override
    public void delete(Long id) {
        Optional<Post> optionalPost = get(id);
        optionalPost.ifPresent(post -> repository.delete(post));
    }
}
