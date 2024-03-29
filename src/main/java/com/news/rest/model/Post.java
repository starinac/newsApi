package com.news.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long postId;

    @NotBlank(message = "Title cannot be empty or Null")
    private String title;
    @Nullable
    @Lob
    private String content;
    @Nullable
    private String source;
    @Nullable
    private String imageUrl;
    @Nullable
    private String urlToPost;
    @OneToOne
    @JoinColumn(name = "imageId", referencedColumnName = "id")
    private Image image;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    @OneToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Category category;
    private Instant datePublished;

}
