package com.renatoganske.dtos;


import com.renatoganske.entities.Customer;
import com.renatoganske.validators.UniqueEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Esse DTO representa um cliente.")
public class CustomerDto {

    private Long id;

    @NotEmpty(message = "{required.name.validation}")
    @Size(min = 3, max = 45, message = "{size.validation}")
    private String name;

    @NotEmpty(message = "{required.email.validation}")
    @Length(min = 3, max = 45)
    @Email
    @UniqueEmail
    private String email;

    @OneToMany
    private Set<ProductDto> favorites;

    public Customer toEntity() {
        return Customer.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .favorites(this.favorites.stream().map(ProductDto::toEntity).collect(Collectors.toSet()))
                .build();
    }
}

