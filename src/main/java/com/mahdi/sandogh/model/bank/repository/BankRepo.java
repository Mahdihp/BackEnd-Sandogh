package com.mahdi.sandogh.model.bank.repository;

import com.mahdi.sandogh.model.bank.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mahdi
 * DateTime: ۱۲/۰۸/۲۰۲۰ - 17:22
 * http://github.com/mahdihp
 * http://gitlab.com/mahdihp
 */
@Repository
public interface BankRepo extends JpaRepository<Bank, Integer> {
}
