package com.renatoganske.controllers;

import com.renatoganske.dtos.ListProductDto;
import com.renatoganske.dtos.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public interface IProductController {

    @GetMapping("/")
    ResponseEntity<ListProductDto> getProductsList(@RequestParam("page") int page);

    @GetMapping("/{id}")
    ResponseEntity<ProductDto> getProductDetail(@PathVariable UUID id);
}
