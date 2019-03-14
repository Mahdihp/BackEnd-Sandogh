package com.mahdi.sandogh.model.installmentLoan.repository;

import com.mahdi.sandogh.model.installmentLoan.InstallmentLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InstallmentLoanRepo extends JpaRepository<InstallmentLoan, Long> {

    Optional<InstallmentLoan> findByUid(UUID uid);

    Optional<InstallmentLoan> findByAmountInstallment(long amount);

    Optional<List<InstallmentLoan>> findByAccountUid(UUID uid);

}
