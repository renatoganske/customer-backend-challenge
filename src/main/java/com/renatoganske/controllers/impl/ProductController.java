package com.renatoganske.controllers.impl;

import com.renatoganske.controllers.IProductController;
import com.renatoganske.dtos.ListProductDto;
import com.renatoganske.dtos.ProductDto;
import com.renatoganske.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController implements IProductController {

    private final ProductService service;

    public ResponseEntity<ListProductDto> getProductsList(int page) {
        return ResponseEntity.ok(service.getProductsList(page));
    }

    public ResponseEntity<ProductDto> getProductDetail(UUID id) {
        return ResponseEntity.ok(service.getProductDetail(id));
    }
}
