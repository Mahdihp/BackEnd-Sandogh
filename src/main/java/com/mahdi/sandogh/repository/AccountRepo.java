package com.mahdi.sandogh.repository;

import com.mahdi.sandogh.model.sandogh.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {


    Boolean existsByNationalCode(String nationalCode);
    Optional<Account> findByUid(UUID uid);
    Optional<Account> findByAccountNumber(String accountNumber);
    Optional<Account> findByMobileNumber(String mobileNumber);
    Optional<Account> findByNationalCode(String nationalCode);
    Optional<List<Account>> findAllByFirstNameLikeOrLastNameLike(String firstName,String lastName);
}
