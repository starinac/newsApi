package com.news.rest.service;

import com.news.rest.dto.FavoriteDto;
import com.news.rest.exceptions.NewsException;
import com.news.rest.exceptions.PostNotFoundException;
import com.news.rest.mapper.FavoriteMapper;
import com.news.rest.model.Favorite;
import com.news.rest.model.Post;
import com.news.rest.model.User;
import com.news.rest.repository.FavoriteRepository;
import com.news.rest.repository.PostRepository;
import com.news.rest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class FavoriteService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final FavoriteRepository favoriteRepository;
    private final FavoriteMapper favoriteMapper;

    public void save(FavoriteDto favoriteDto){
        Post post = postRepository.findById(favoriteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(favoriteDto.getPostId().toString()));
        Optional<Favorite> favoriteByPostAndUser = favoriteRepository.findByPostAndUser(post, authService.getCurrentUser());
        if(favoriteByPostAndUser.isPresent()){
            throw new NewsException("You have already added this post to favorites");
        }
        Favorite favorite = favoriteMapper.map(favoriteDto, post, authService.getCurrentUser());
        favoriteRepository.save(favorite);
    }

    public List<FavoriteDto> getAllFavoritesForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return favoriteRepository.findAllByUser(user)
                .stream()
                .map(favoriteMapper::mapToDto).collect(toList());
    }
}
