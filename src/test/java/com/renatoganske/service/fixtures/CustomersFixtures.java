package com.renatoganske.service.fixtures;

import com.renatoganske.dtos.CustomerDto;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CustomersFixtures {

    public static CustomerDto customer() {
        return CustomerDto.builder()
                .id(1L)
                .name("John")
                .email("john@email.com")
                .favorites(Set.of())
                .build();
    }

    public static List<CustomerDto> getAllCustomers() {
        return Arrays.asList(CustomerDto.builder()
                        .id(1L)
                        .name("John")
                        .email("john@email.com")
                        .favorites(Set.of())
                        .build(),
                CustomerDto.builder()
                        .id(2L)
                        .name("Jane")
                        .email("jane@email.com")
                        .favorites(Set.of())
                        .build()
        );
    }
}
