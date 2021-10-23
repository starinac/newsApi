package com.news.rest.service;

import com.news.rest.dto.PostDto;
import com.news.rest.model.Post;
import com.news.rest.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
@AllArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostDto save(PostDto postDto){
        Post save = postRepository.save(mapPostDto(postDto));
        postDto.setPostId(save.getPostId());
        return postDto;
    }

    @Transactional(readOnly = true)
    public List<PostDto> getAll() {
        return postRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    private PostDto mapToDto(Post post) {
        return PostDto.builder().title(post.getTitle())
                .content(post.getContent())
                .source(post.getSource())
                .postId(post.getPostId())
                .build();
    }

    private Post mapPostDto(PostDto postDto) {
        return Post.builder().title(postDto.getTitle())
                .content(postDto.getContent())
                .source(postDto.getSource())
                .build();
    }

}
