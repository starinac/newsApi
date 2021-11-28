package com.news.rest.controller;

import com.news.rest.dto.CommentDto;
import com.news.rest.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/comments/")
@AllArgsConstructor
public class CommentsController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto){
        commentService.save(commentDto);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsForPost(@PathVariable Long postId){
        return ResponseEntity.status(OK)
                .body(commentService.getAllCommentsForPost(postId));
    }
}
