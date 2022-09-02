package com.news.rest.controller;

import com.news.rest.dto.PostDto;
import com.news.rest.model.Image;
import com.news.rest.repository.ImageRepository;
import com.news.rest.util.ImageUtility;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/image")
@AllArgsConstructor
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @PostMapping("/upload/{id}")
    public Image uploadImage(@PathVariable Long id, @RequestParam("myFile") MultipartFile file) throws IOException {

        Image img = new Image( file.getOriginalFilename(),file.getContentType(),file.getBytes(), id);

        return imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .postId(id)
                .pic(ImageUtility.compressImage(file.getBytes())).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id){
        Optional<Image> image = Optional.of(imageRepository.getById(id));
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(image.get().getType()))
                .body(ImageUtility.decompressImage(image.get().getPic()));
    }
}
