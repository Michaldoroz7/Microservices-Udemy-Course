package com.mdoroz.rest.webservices.restfulwebservices.repository;

import com.mdoroz.rest.webservices.restfulwebservices.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
