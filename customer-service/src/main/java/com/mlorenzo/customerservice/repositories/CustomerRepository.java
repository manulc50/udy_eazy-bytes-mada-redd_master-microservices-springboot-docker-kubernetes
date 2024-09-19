package com.mlorenzo.customerservice.repositories;

import com.mlorenzo.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByMobileNumber(String mobileNumber);
    List<Customer> findByMobileNumberOrEmail(String mobileNumber, String email);
}
