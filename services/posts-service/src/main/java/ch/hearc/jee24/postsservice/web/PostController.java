package ch.hearc.jee24.postsservice.web;

import ch.hearc.jee24.postsservice.service.Post;
import ch.hearc.jee24.postsservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping
    @ResponseBody ResponseEntity<Post> createPost(@RequestBody Post post){
        Post newPost = postService.create(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseBody ResponseEntity<List<Post>> readPosts(){
        List<Post> posts = postService.getAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody ResponseEntity<Post> readPost(@PathVariable Long id){
        Optional<Post> optionalPost = postService.get(id);
        return optionalPost
                .map(post -> new ResponseEntity<>(post, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @ResponseBody ResponseEntity<Post> updatePost(@RequestBody Post post, @PathVariable Long id){
        Post updatedPost = postService.update(id, post);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody ResponseEntity<Post> deletePost(@PathVariable Long id){
        postService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
