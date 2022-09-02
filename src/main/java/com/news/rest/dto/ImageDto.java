package com.news.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDto {
    private Long id;
    private String name;
    private String type;
    private byte[] pic;
    private Long postId;
}
