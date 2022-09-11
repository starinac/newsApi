package com.news.rest.service;

import com.news.rest.dto.PostDto;
import com.news.rest.exceptions.NewsException;
import com.news.rest.mapper.PostMapper;
import com.news.rest.model.Image;
import com.news.rest.model.Post;
import com.news.rest.model.User;
import com.news.rest.repository.PostRepository;
import com.news.rest.repository.UserRepository;
import com.news.rest.util.ImageUtility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final AuthService authService;
    private final PostMapper postMapper;
    private final UserRepository userRepository;

    @Transactional
    public Post save(PostDto postDto) {
        User currentUser = authService.getCurrentUser();
        postDto.setDatePublished(Instant.now());
        return postRepository.save(postMapper.map(postDto, currentUser));
    }

    @Transactional(readOnly = true)
    public List<PostDto> getAll() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapPostToDto)
                .collect(toList());
    }

    public PostDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NewsException("No post found with that id"));
        return postMapper.mapPostToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostDto> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapPostToDto)
                .collect(toList());
    }
}
