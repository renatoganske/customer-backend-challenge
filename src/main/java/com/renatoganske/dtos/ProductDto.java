package com.renatoganske.dtos;

import com.renatoganske.entities.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "Esse DTO representa um produto.")
public class ProductDto {

    private UUID id;
    private String title;
    private String image;
    private Double price;
    private Double reviewScore;

    public Product toEntity() {
        return Product.builder()
                .id(this.id)
                .title(this.title)
                .price(this.price)
                .image(this.image)
                .reviewScore(this.reviewScore)
                .build();
    }
}
