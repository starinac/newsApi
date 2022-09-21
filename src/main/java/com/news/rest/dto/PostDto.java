package com.news.rest.dto;

import com.news.rest.model.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private Long postId;
    private String title;
    private String content;
    private String source;
    private Instant datePublished;
    private Integer commentCount;
    private String imageUrl;
    private String urlToPost;
    private Image image;
    private Long categoryId;
}
