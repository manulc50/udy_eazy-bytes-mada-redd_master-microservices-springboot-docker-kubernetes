package com.mlorenzo.customerservice.services;

import com.mlorenzo.customerservice.dtos.CustomerDto;

public interface CustomerService {
    CustomerDto getByMobileNumber(String mobileNumber);
    CustomerDto create(CustomerDto customerDto);
    void updateByMobileNumber(CustomerDto customerDto, String mobileNumber);
    void deleteByMobileNumber(String mobileNumber);
}
