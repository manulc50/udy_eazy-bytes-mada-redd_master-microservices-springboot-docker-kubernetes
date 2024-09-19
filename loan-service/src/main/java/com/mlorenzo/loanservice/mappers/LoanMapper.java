package com.mlorenzo.loanservice.mappers;

import com.mlorenzo.loanservice.dtos.LoanDto;
import com.mlorenzo.loanservice.entities.Loan;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    public LoanDto mapToDto(Loan loan) {
        LoanDto loanDto = new LoanDto();
        BeanUtils.copyProperties(loan, loanDto);
        return loanDto;
    }

    public Loan mapToEntity(LoanDto loanDto) {
        Loan loan = new Loan();
        BeanUtils.copyProperties(loanDto, loan);
        return loan;
    }
}
