package com.renatoganske.clients;

import com.renatoganske.dtos.ListProductDto;
import com.renatoganske.dtos.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "products-api", url = "${external.magalu.products.url}")
public interface IProductApiClient {

    @Operation(summary = "Products List", description = "Post para buscar a lista de produtos.")
    @GetMapping("/?page={page}")
    ListProductDto getProductsList(@PathVariable("page") int page);

    @Operation(summary = "Token", description = "Post para buscar os detalhes de um determinado produto.")
    @GetMapping("/{id}/")
    ProductDto getProductDetail(@PathVariable("id") UUID id);
}
