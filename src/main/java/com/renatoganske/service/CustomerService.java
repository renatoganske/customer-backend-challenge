package com.renatoganske.service;


import com.renatoganske.clients.impl.ProducApiClient;
import com.renatoganske.dtos.CustomerDto;
import com.renatoganske.entities.Customer;
import com.renatoganske.errorHandling.exception.business.ObjectNotFoundException;
import com.renatoganske.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {

    private final ProducApiClient client;

    private final CustomerRepository customerRepository;

    public List<CustomerDto> findAll() {
        log.info("Buscando todos os clientes.");
        return customerRepository.findAll().stream().map(Customer::toDto).toList();
    }

    public CustomerDto findById(Long id) {

        log.info("Buscando o cliente. Id: {}", id);
        Optional<Customer> customer = getCustomer(id);
        if (customer.isPresent()) {
            return customer.get().toDto();
        }
        log.error("Não foi possível encontrar o usuário. ID: {}", id);
        throw new ObjectNotFoundException();
    }

    public Customer saveUser(CustomerDto customerDto) {
        log.info("Criando novo cliente. Nome: {}, Email: {}", customerDto.getName(), customerDto.getEmail());
        return customerRepository.save(customerDto.toEntity());
    }

    public Customer updateUser(Long id, CustomerDto updatingCostumer) {
        log.info("Atualizando o cliente de ID: {}", updatingCostumer.getId());
        Optional<Customer> customer = getCustomer(id);
        if (customer.isPresent()) {
            Customer updating = customer.get();
            updating.setName(updatingCostumer.getName());
            updating.setEmail(updatingCostumer.getEmail());
            return customerRepository.save(updating);
        }
        log.error("Não foi possível atualizar o usuário. ID: {}", updatingCostumer.getId());
        throw new ObjectNotFoundException();
    }

    public void delete(Long id) {
        log.info("Excluindo o usuário de ID:{}", id);
            customerRepository.deleteById(id);
    }

    public CustomerDto addProductToFavorites(Long customerId, UUID productId) {
        log.info("Adicionando o produto ID: {} ba lista de favoritos do usuário ID: {}", productId, customerId);
        Optional<Customer> customer = getCustomer(customerId);
        if (customer.isPresent()) {
            customer.get().getFavorites().add(client.getProductDetail(productId).toEntity());
            return customerRepository.save(customer.get()).toDto();
        }
        log.info("Não foi possível adicionar o produto ID: {} na lista de favoritos do usuário ID: {}", productId, customerId);
        throw new ObjectNotFoundException();
    }

    private Optional<Customer> getCustomer(Long id) {
        return customerRepository.findById(id);
    }
}
