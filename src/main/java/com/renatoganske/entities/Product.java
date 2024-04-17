package com.renatoganske.entities;

import com.renatoganske.dtos.ProductDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    @Column(nullable = false)
    private UUID id;
    @Column
    private String title;
    @Column
    private Double price;
    @Column
    private String image;
    @Column
    private String brand;
    @Column(name = "review_score")
    private Double reviewScore;

    public ProductDto toDto() {
        return ProductDto.builder()
                .id(this.id)
                .title(this.title)
                .price(this.price)
                .image(this.image)
                .reviewScore(this.reviewScore)
                .build();
    }
}
