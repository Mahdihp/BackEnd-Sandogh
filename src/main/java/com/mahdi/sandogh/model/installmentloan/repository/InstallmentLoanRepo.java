package com.mahdi.sandogh.model.installmentloan.repository;

import com.mahdi.sandogh.model.installmentloan.InstallmentLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InstallmentLoanRepo extends JpaRepository<InstallmentLoan, Long> {

    Optional<InstallmentLoan> findById(Long uid);

    Optional<InstallmentLoan> findByAmountInstallment(long amount);

    Optional<List<InstallmentLoan>> findByAccountId(Long uid);

}
