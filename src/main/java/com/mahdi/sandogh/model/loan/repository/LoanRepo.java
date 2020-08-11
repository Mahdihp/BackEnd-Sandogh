package com.mahdi.sandogh.model.loan.repository;

import com.mahdi.sandogh.model.loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoanRepo extends JpaRepository<Loan, Long> {


    Optional<Loan> findById(Long id);

    Optional<Loan> findByCurrentLoanAmount(long amount);

    Optional<List<Loan>> findAllByAccountId(Long id);


}
