package com.mlorenzo.customerservice.mappers;

import com.mlorenzo.customerservice.dtos.AccountDto;
import com.mlorenzo.customerservice.dtos.CustomerDto;
import com.mlorenzo.customerservice.entities.Account;
import com.mlorenzo.customerservice.entities.Customer;
import org.springframework.beans.BeanUtils;

public class CustomerMapper {

    public static Customer mapToEntity(CustomerDto customerDto) {
        Account account = new Account();
        BeanUtils.copyProperties(customerDto.getAccount(), account);
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        customer.addAccount(account);
        return customer;
    }

    public static CustomerDto mapToDto(Customer customer) {
        AccountDto accountDto = new AccountDto();
        BeanUtils.copyProperties(customer.getAccount(), accountDto);
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto);
        customerDto.setAccount(accountDto);
        return customerDto;
    }
}
