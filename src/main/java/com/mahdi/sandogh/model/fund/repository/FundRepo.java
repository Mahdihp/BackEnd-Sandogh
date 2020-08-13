package com.mahdi.sandogh.model.fund.repository;

import com.mahdi.sandogh.model.account.Account;
import com.mahdi.sandogh.model.fund.Fund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mahdi
 * DateTime: ۱۲/۰۸/۲۰۲۰ - 17:23
 * http://github.com/mahdihp
 * http://gitlab.com/mahdihp
 */

@Repository
public interface FundRepo extends JpaRepository<Fund, Integer> {

    List<Fund> findAllByDisplayNameAndDeleted(String displayName, Boolean deleted);
    List<Fund> findAllByDeleted(Boolean deleted);
    List<Account> findAllByAccountsIsNotId(Integer fundId);
    List<Account> findAllByAccountsAndIdEquals(Integer fundId);

}
