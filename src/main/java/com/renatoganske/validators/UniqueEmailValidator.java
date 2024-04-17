package com.renatoganske.validators;

import com.renatoganske.repository.CustomerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !customerRepository.existsByEmail(email);
    }
}
