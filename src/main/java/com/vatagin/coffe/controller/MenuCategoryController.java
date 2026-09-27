package com.vatagin.coffe.controller;

import com.vatagin.coffe.dto.request.CreateMenuCategoryRequest;
import com.vatagin.coffe.dto.response.MenuCategoryResponse;
import com.vatagin.coffe.service.MenuCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu-categories")
@RequiredArgsConstructor
public class MenuCategoryController {
    private final MenuCategoryService categoryService;

    @GetMapping
    public List<MenuCategoryResponse> getAllByCafe(@RequestParam Long cafeId) {
        return categoryService.getAllByCafe(cafeId);
    }

    @GetMapping("/{id}")
    public MenuCategoryResponse getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MenuCategoryResponse create(@Valid @RequestBody CreateMenuCategoryRequest request) {
        return categoryService.create(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
