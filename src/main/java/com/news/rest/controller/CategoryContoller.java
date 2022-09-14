package com.news.rest.controller;

import com.news.rest.dto.FavoriteDto;
import com.news.rest.model.Category;
import com.news.rest.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryContoller {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.status(OK)
                .body(categoryService.getAllCategories());
    }
}
