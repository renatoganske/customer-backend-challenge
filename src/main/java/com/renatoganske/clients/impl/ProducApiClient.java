package com.renatoganske.clients.impl;

import com.renatoganske.clients.IProductApiClient;
import com.renatoganske.dtos.ListProductDto;
import com.renatoganske.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProducApiClient {

    private final IProductApiClient client;

    public ListProductDto getProductsList(int page) {
        log.info("Listando os produtos da página: {} ", page);
        return client.getProductsList(page);
    }

    public ProductDto getProductDetail(UUID id) {
        log.info("Buscando os detalhes do produto. ID:{} ", id);
        return client.getProductDetail(id);
    }
}
