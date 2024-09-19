package com.mlorenzo.loanservice.services;

import com.mlorenzo.loanservice.dtos.LoanDto;
import com.mlorenzo.loanservice.dtos.LoanRequestDto;

import java.math.BigDecimal;

public interface LoanService {
    LoanDto fetchById(Long loanNumber);
    LoanDto create(LoanRequestDto loanRequestDto);
    void payAmount(BigDecimal amount, Long loanNumber);
    void deleteById(Long loanNumber);
}
