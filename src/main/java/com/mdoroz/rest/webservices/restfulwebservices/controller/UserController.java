package com.mdoroz.rest.webservices.restfulwebservices.controller;

import com.mdoroz.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.mdoroz.rest.webservices.restfulwebservices.repository.PostRepository;
import com.mdoroz.rest.webservices.restfulwebservices.repository.UserRepository;
import com.mdoroz.rest.webservices.restfulwebservices.user.Post;
import com.mdoroz.rest.webservices.restfulwebservices.user.User;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {


    private UserRepository userRepository;
    private PostRepository postRepository;


    public UserController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> getSpecifiedUser(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        entityModel.add(linkBuilder.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteSpecifiedUser(@PathVariable Integer id) {
        userRepository.deleteById(id);

    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getUserPosts(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }

        return user.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Post> createUserPost(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }
        post.setUser(user.get());
        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
