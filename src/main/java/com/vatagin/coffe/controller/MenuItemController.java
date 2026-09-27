package com.vatagin.coffe.controller;

import com.vatagin.coffe.dto.request.CreateMenuItemRequest;
import com.vatagin.coffe.dto.response.MenuItemResponse;
import com.vatagin.coffe.service.MenuItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu-items")
@RequiredArgsConstructor
public class MenuItemController {
    private final MenuItemService menuItemService;

    @GetMapping
    public List<MenuItemResponse> getAllByCategory(
            @RequestParam Long categoryId,
            @RequestParam(defaultValue = "false") boolean onlyAvailable) {
        return menuItemService.getAllByCategory(categoryId, onlyAvailable);
    }

    @GetMapping("/{id}")
    public MenuItemResponse getById(@PathVariable Long id) {
        return menuItemService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MenuItemResponse create(@Valid @RequestBody CreateMenuItemRequest request) {
        return menuItemService.create(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        menuItemService.delete(id);
    }
}
