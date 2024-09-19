package com.mlorenzo.loanservice.repositories;

import com.mlorenzo.loanservice.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
