package com.renatoganske.controllers;

import com.renatoganske.dtos.CustomerDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public interface ICustomerController {

    @Operation(summary = "Listar clientes", description = "Busca todos os clientes.")
    @GetMapping
    ResponseEntity<List<CustomerDto>> findAll();

    @Operation(summary = "Buscar cliente", description = "Busca um determinado cliente pelo id.")
    @GetMapping("/{id}")
    ResponseEntity<CustomerDto> findById(@PathVariable Long id);

    @Operation(summary = "Salvar cliente", description = "Salva um novo cliente.")
    @PostMapping
    ResponseEntity<CustomerDto> saveUser(@Valid @RequestBody CustomerDto customerDto);

    @Operation(summary = "Editar cliente", description = "Edita um determinado cliente.")
    @PutMapping("/{id}")
    ResponseEntity<CustomerDto> updateUser(@PathVariable Long id, @Valid @RequestBody CustomerDto customerDto);

    @Operation(summary = "Excluir cliente", description = "Exclui um determinado cliente.")
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);

    @PostMapping("/{customerId}/favorites/{productId}")
    ResponseEntity<CustomerDto> addFavoriteProductToCustomer(
            @PathVariable Long customerId,
            @PathVariable UUID productId);

}
