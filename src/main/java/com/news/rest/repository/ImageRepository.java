package com.news.rest.repository;

import com.news.rest.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;


public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query("select i from Image i where i.postId = :id")
    Image getByPostId(@Param("id") Long id);
}
