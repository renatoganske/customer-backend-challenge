package com.renatoganske.controllers.impl;

import com.renatoganske.controllers.ICustomerController;
import com.renatoganske.dtos.CustomerDto;
import com.renatoganske.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController implements ICustomerController {

    private final CustomerService service;

    @Override
    public ResponseEntity<List<CustomerDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<CustomerDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok((service.findById(id)));
    }

    @Override
    public ResponseEntity<CustomerDto> saveUser(
            @Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(service.saveUser(customerDto).toDto());
    }

    @Override
    public ResponseEntity<CustomerDto> updateUser(
            @PathVariable Long customerId,
            @Valid @RequestBody CustomerDto customerDto) {

        return ResponseEntity.ok(service.updateUser(customerId, customerDto).toDto());
    }

    @Override
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @Override
    public ResponseEntity<CustomerDto> addFavoriteProductToCustomer(
            @PathVariable Long customerId,
            @PathVariable UUID productId) {
        return ResponseEntity.ok(service.addProductToFavorites(customerId, productId));
    }
}
