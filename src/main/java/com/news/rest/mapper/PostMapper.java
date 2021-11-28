package com.news.rest.mapper;

import com.news.rest.dto.PostDto;
import com.news.rest.model.Post;
import com.news.rest.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostDto mapPostToDto(Post post);

    @Mapping(target = "datePublished", expression = "java(java.time.Instant.now())")
    Post map(PostDto postDto, User user);
}
