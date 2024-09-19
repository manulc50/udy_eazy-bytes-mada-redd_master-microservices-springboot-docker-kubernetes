package com.mlorenzo.customerservice.services.impl;

import com.mlorenzo.customerservice.dtos.CustomerDto;
import com.mlorenzo.customerservice.entities.Customer;
import com.mlorenzo.customerservice.exceptions.CustomerAlreadyExistsException;
import com.mlorenzo.customerservice.exceptions.CustomerNotFoundException;
import com.mlorenzo.customerservice.mappers.CustomerMapper;
import com.mlorenzo.customerservice.repositories.CustomerRepository;
import com.mlorenzo.customerservice.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Random;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final Random random = new Random();

    @Override
    public CustomerDto getByMobileNumber(String mobileNumber) {
        return customerRepository.findByMobileNumber(mobileNumber)
                // Versión simplificada de la expresión "customer -> CustomerMapper.mapToDto(customer)"
                .map(CustomerMapper::mapToDto)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer with mobile number " + mobileNumber + " not found"));
    }

    @Override
    public CustomerDto create(CustomerDto customerDto) {
        checkExistingCustomer(customerDto);
        Customer customer = CustomerMapper.mapToEntity(customerDto);
        customer.getAccount().setNumber(1000000000L + random.nextLong(900000000L));
        return CustomerMapper.mapToDto(customerRepository.save(customer));
    }

    @Override
    public void updateByMobileNumber(CustomerDto customerDto, String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer with mobile number " + mobileNumber + " not found"));
        if(!customer.getEmail().equals(customerDto.getEmail()) || !customer.getMobileNumber().equals(customerDto.getMobileNumber()))
            checkExistingCustomer(customerDto);
        BeanUtils.copyProperties(customerDto.getAccount(), customer.getAccount(), "number");
        BeanUtils.copyProperties(customerDto, customer);
        customerRepository.save(customer);
    }

    @Override
    public void deleteByMobileNumber(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer with mobile number " + mobileNumber + " not found"));
        customerRepository.deleteById(customer.getId());
    }

    private void checkExistingCustomer(CustomerDto customerDto) {
        if(!customerRepository.findByMobileNumberOrEmail(customerDto.getMobileNumber(), customerDto.getEmail()).isEmpty())
            throw new CustomerAlreadyExistsException("Customer already registered with given mobile number or given email");
    }
}
