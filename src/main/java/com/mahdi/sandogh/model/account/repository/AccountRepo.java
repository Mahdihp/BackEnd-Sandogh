package com.mahdi.sandogh.model.account.repository;

import com.mahdi.sandogh.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {


    Boolean existsByNationalCode(String nationalCode);
    Optional<Account> findByAccountNumber(String accountNumber);

    Optional<Account> findByMobileNumber(String mobileNumber);
    Optional<Account> findByNationalCode(String nationalCode);
    Optional<List<Account>> findAllByFirstNameLikeOrLastNameLike(String firstName,String lastName);

    @Query("SELECT max(acc.accountNumber) FROM Account acc")
    Long findMaxAccountNumber();
}
