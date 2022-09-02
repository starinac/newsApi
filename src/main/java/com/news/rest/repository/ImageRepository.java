package com.news.rest.repository;

import com.news.rest.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<Image, Long> {
}
