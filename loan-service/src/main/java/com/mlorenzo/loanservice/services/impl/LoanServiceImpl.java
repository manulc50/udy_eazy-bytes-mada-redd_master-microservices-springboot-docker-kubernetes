package com.mlorenzo.loanservice.services.impl;

import com.mlorenzo.loanservice.dtos.LoanDto;
import com.mlorenzo.loanservice.dtos.LoanRequestDto;
import com.mlorenzo.loanservice.entities.Loan;
import com.mlorenzo.loanservice.mappers.LoanMapper;
import com.mlorenzo.loanservice.repositories.LoanRepository;
import com.mlorenzo.loanservice.services.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

@AllArgsConstructor
@Service
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;
    private final Random random = new Random();

    @Override
    public LoanDto fetchById(Long loanNumber) {
        Loan loan = loanRepository.findById(loanNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Loan with number " + loanNumber + " not found"));
        return loanMapper.mapToDto(loan);
    }

    @Override
    public LoanDto create(LoanRequestDto loanRequestDto) {
        Loan loan = new Loan();
        loan.setTotal(loanRequestDto.getTotal());
        loan.setCustomerMobileNumber(loanRequestDto.getCustomerMobileNumber());
        loan.setType(loanRequestDto.getType());
        loan.setAmountPaid(BigDecimal.ZERO);
        loan.setOutstandingAmount(loanRequestDto.getTotal());
        loan.setNumber(1_000_000_000 + random.nextLong(900_000_000));
        return loanMapper.mapToDto(loanRepository.save(loan));
    }

    @Override
    public void payAmount(BigDecimal amount, Long loanNumber) {
        AtomicReference<BigDecimal> atomicAmount = new AtomicReference<>(amount);
        loanRepository.findById(loanNumber)
                .ifPresentOrElse(loan -> {
                    BigDecimal outstandingAmount = loan.getOutstandingAmount();
                    if (outstandingAmount.compareTo(BigDecimal.ZERO) > 0) {
                        if(atomicAmount.get().compareTo(outstandingAmount) > 0)
                            atomicAmount.set(outstandingAmount);
                        loan.setAmountPaid(loan.getAmountPaid().add(atomicAmount.get()));
                        loan.setOutstandingAmount(loan.getOutstandingAmount().subtract(atomicAmount.get()));
                        loanRepository.save(loan);
                    }
                    else {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "The loan with number " + loanNumber + " is paid");
                    }
                },
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Loan with number " + loanNumber + " not found");
                });
    }

    @Override
    public void deleteById(Long loanNumber) {
        if(loanRepository.existsById(loanNumber))
            loanRepository.deleteById(loanNumber);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan with number " + loanNumber + " not found");
    }
}
