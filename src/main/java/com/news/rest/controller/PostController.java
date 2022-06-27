package com.news.rest.controller;

import com.news.rest.dto.PostDto;
import com.news.rest.model.Post;
import com.news.rest.service.AuthService;
import com.news.rest.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/post")
@AllArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostDto postDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.save(postDto));
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(postService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.getPost(id));
    }

    @GetMapping("by-user/{name}")
    public ResponseEntity<List<PostDto>> getPostsByUsername(@PathVariable String name) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.getPostsByUsername(name));
    }

    @GetMapping("/payed/{username}")
    public ResponseEntity<String> payedSubscription(@PathVariable String username) {
        authService.updatePayment(username);
        return new ResponseEntity<>("Subscription payed successfully", OK);
    }
}
