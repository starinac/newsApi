package com.news.rest.repository;

import com.news.rest.model.Favorite;
import com.news.rest.model.Post;
import com.news.rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findAllByUser(User user);
    Optional<Favorite> findByPost(Post post);
    Optional<Favorite> findByPostAndUser(Post post, User user);
}
