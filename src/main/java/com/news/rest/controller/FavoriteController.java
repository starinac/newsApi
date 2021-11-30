package com.news.rest.controller;

import com.news.rest.dto.FavoriteDto;
import com.news.rest.service.FavoriteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/favorites/")
@AllArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<Void> addFavorite(@RequestBody FavoriteDto favoriteDto){
        favoriteService.save(favoriteDto);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<List<FavoriteDto>> getAllFavoritesForUser(@PathVariable String userName){
        return ResponseEntity.status(OK)
                .body(favoriteService.getAllFavoritesForUser(userName));
    }
}
