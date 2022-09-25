package com.news.rest.mapper;

import com.news.rest.dto.FavoriteDto;
import com.news.rest.model.Favorite;
import com.news.rest.model.Post;
import com.news.rest.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FavoriteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateAdded", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    Favorite map(FavoriteDto favoriteDto, Post post, User user);

    @Mapping(target = "postId", expression = "java(favorite.getPost().getPostId())")
    @Mapping(target = "userName", expression = "java(favorite.getUser().getUsername())")
    @Mapping(target = "post", source = "post")
    FavoriteDto mapToDto(Favorite favorite);
}
