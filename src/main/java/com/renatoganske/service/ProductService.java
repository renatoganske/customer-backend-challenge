package com.renatoganske.service;

import com.renatoganske.clients.impl.ProducApiClient;
import com.renatoganske.dtos.CustomerDto;
import com.renatoganske.dtos.ListProductDto;
import com.renatoganske.dtos.ProductDto;
import com.renatoganske.entities.Customer;
import com.renatoganske.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

    private final ProducApiClient client;
    private final CustomerRepository customerRepository;

    public ListProductDto getProductsList(int page) {
        log.info("Listando todos os produtos da API externa.", page);
           return client.getProductsList(page);
    }

    public ProductDto getProductDetail(UUID id) {
        log.info("Buscando os detalhes do produto ID: {}", id);
        return client.getProductDetail(id);
    }
}
