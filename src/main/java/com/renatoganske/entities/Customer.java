package com.renatoganske.entities;

import com.renatoganske.dtos.CustomerDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table (name="customers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 45)
    private String email;

    @Fetch(value = FetchMode.SELECT)
    @Builder.Default
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Product> favorites = new HashSet<>();

    public CustomerDto toDto() {
        return CustomerDto.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .favorites(this.favorites.stream().map(Product::toDto).collect(Collectors.toSet()))
                .build();
    }
}
