package com.news.rest.mapper;

import com.news.rest.dto.PostDto;
import com.news.rest.model.Category;
import com.news.rest.model.Post;
import com.news.rest.model.User;
import com.news.rest.repository.CommentRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;

    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    public abstract PostDto mapPostToDto(Post post);

    @Mapping(target = "datePublished", expression = "java(java.time.Instant.now())")
    @Mapping(target = "category", source = "category")
    public abstract Post map(PostDto postDto, User user, Category category);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }
}
