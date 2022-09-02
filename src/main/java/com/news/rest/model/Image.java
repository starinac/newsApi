package com.news.rest.model;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Image {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String type;
    @Lob
    private byte[] pic;
    @JoinColumn(name = "postid", referencedColumnName = "postId")
    private Long postId;

    public Image(String name, String type, byte[] pic, Long postId){
        this.name = name;
        this.type = type;
        this.pic = pic;
        this.postId = postId;
    }
}
