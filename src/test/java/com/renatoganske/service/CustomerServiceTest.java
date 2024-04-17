package com.renatoganske.service;

import com.renatoganske.clients.impl.ProducApiClient;
import com.renatoganske.dtos.CustomerDto;
import com.renatoganske.dtos.ProductDto;
import com.renatoganske.entities.Customer;
import com.renatoganske.errorHandling.exception.business.ObjectNotFoundException;
import com.renatoganske.repository.CustomerRepository;
import com.renatoganske.service.fixtures.CustomersFixtures;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest()
@WebAppConfiguration
class CustomerServiceTest {
    @Mock
    ProducApiClient client;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    CustomerService customerService;

    @Test
    void whenFindAllShouldReturnSuccess() {
        List<CustomerDto> customerDtos = CustomersFixtures.getAllCustomers();
        when(customerRepository.findAll()).thenReturn(customerDtos.stream()
                .map(CustomerDto::toEntity).collect(Collectors.toList()));

        List<CustomerDto> result = customerService.findAll();
        MatcherAssert.assertThat(result.size(), is(2));
    }

    @Test
    void whenFindByIdShouldReturnSuccess() {

        Customer customer = CustomersFixtures.customer().toEntity();
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

        CustomerDto result = customerService.findById(customer.getId());

        assertEquals(customer.getId(), result.getId());
        assertEquals(customer.getName(), result.getName());
        assertEquals(customer.getEmail(), result.getEmail());
    }

    @Test
    void whenSaveUserShouldReturnSuccess() {

        CustomerDto customerToSave = CustomersFixtures.customer();

        when(customerRepository.save(any())).thenAnswer(invocation -> {
            Customer customerSaved = invocation.getArgument(0);
            customerSaved.setId(1L);
            return customerSaved;
        });

        Customer result = customerService.saveUser(customerToSave);

        assertEquals("John", result.getName());
        assertEquals("john@email.com", result.getEmail());
        assertEquals(1L, result.getId());
    }

    @Test
    void updateUserShouldReturnUpdatedCustomer() {
        Long customerId = 1L;
        CustomerDto updatingDto = CustomerDto.builder()
                .id(customerId)
                .name("John Updated")
                .email("john_updated@example.com")
                .favorites(Set.of())
                .build();

        Customer existingCustomer = CustomersFixtures.customer().toEntity();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Customer result = customerService.updateUser(customerId, updatingDto);
        assertEquals("John Updated", result.getName());
        assertEquals("john_updated@example.com", result.getEmail());
        verify(customerRepository).findById(customerId);
        verify(customerRepository).save(existingCustomer);
    }

    @Test
    void updateUserWithNonExistentIdShouldThrowException() {

        Long nonExistentId = 50L;
        CustomerDto updatingDto = CustomerDto.builder()
                .id(nonExistentId)
                .name("John Updated")
                .email("john_updated@example.com")
                .favorites(Set.of())
                .build();

        when(customerRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class,
                () -> customerService.updateUser(nonExistentId, updatingDto));
        verify(customerRepository).findById(nonExistentId);
        verify(customerRepository, never()).save(any());
    }

    @Test
    void deleteUserShouldDeleteCustomerIfExists() {
        Long customerId = 1L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(new Customer()));
        assertDoesNotThrow(() -> customerService.delete(customerId));
        verify(customerRepository).deleteById(customerId);
    }

    @Test
    void addProductToFavoritesShouldAddProductToCustomerFavorites() {
        Long customerId = 1L;
        UUID productId = UUID.randomUUID();
        ProductDto productDto = new ProductDto(productId, "Product Title", "Product Image", 120.2, null);
        Customer customer = CustomersFixtures.customer().toEntity();

        when(client.getProductDetail(productId)).thenReturn(productDto);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDto updatedCustomerDto = customerService.addProductToFavorites(customerId, productId);

        assertTrue(updatedCustomerDto.getFavorites().stream().anyMatch(p -> p.getId().equals(productId)));
        verify(client).getProductDetail(productId);
        verify(customerRepository).findById(customerId);
        verify(customerRepository).save(customer);
    }
}
