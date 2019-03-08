package com.mahdi.sandogh.service;

import com.mahdi.sandogh.model.dto.AccountDTO;
import com.mahdi.sandogh.model.dto.AccountForm;
import com.mahdi.sandogh.model.sandogh.Account;
import com.mahdi.sandogh.repository.AccountRepo;
import com.mahdi.sandogh.utils.Constants;
import com.mahdi.sandogh.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    AccountRepo accountRepo;

    public boolean createAccount(AccountForm accountForm) {
        Boolean exists = accountRepo.existsByNationalCode(accountForm.getNationalCode());
        if (!exists) {
            Account account = new Account();
            account.setUid(UUID.randomUUID());
            account.setAccountNumber(DataUtil.generateNumericRandomAccountNumber(10));

            account.setFirstName(accountForm.getFirstName());
            account.setLastName(accountForm.getLastName());
            account.setFatherName(accountForm.getFatherName());
            account.setMobileNumber(accountForm.getMobileNumber());
            account.setNationalCode(accountForm.getNationalCode());
            account.setCity(accountForm.getCity());
            account.setAdderss(accountForm.getAdderss());
            account.setCreationDate(System.currentTimeMillis());
            accountRepo.save(account);
            return true;
        }
        return false;
    }

    public boolean updateAccount(AccountForm accountForm) {
        Optional<Account> account = accountRepo.findByUid(UUID.fromString(accountForm.getAccountId()));
        if (account.isPresent()) {
            account.get().setFirstName(accountForm.getFirstName());
            account.get().setLastName(accountForm.getLastName());
            account.get().setFatherName(accountForm.getFatherName());
            account.get().setMobileNumber(accountForm.getMobileNumber());
            account.get().setNationalCode(accountForm.getNationalCode());
            account.get().setCity(accountForm.getCity());
            account.get().setAdderss(accountForm.getAdderss());
            account.get().setModificationDate(System.currentTimeMillis());
            accountRepo.save(account.get());
            return true;
        }
        return false;
    }

    public Optional<Account> findUid(String uid) {
        Optional<Account> account = accountRepo.findByUid(UUID.fromString(uid));
        if (account.isPresent())
            return Optional.ofNullable(account.get());
        else
            return Optional.empty();
    }

    public Optional<AccountDTO> findAccountDTOByUid(String uid) {
        Optional<Account> account = accountRepo.findByUid(UUID.fromString(uid));
        if (account.isPresent()) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setStatus(HttpStatus.OK.value());
            accountDTO.setMessage(Constants.KEY_SUCESSE);
            accountDTO.setFirstName(account.get().getFirstName());
            accountDTO.setLastName(account.get().getLastName());
            accountDTO.setFatherName(account.get().getFatherName());
            accountDTO.setMobileNumber(account.get().getMobileNumber());
            accountDTO.setNationalCode(account.get().getNationalCode());
            accountDTO.setCity(account.get().getCity());
            accountDTO.setAdderss(account.get().getAdderss());
            accountDTO.setCreationDate(account.get().getCreationDate());
            return Optional.ofNullable(accountDTO);
        }
        return Optional.empty();
    }

}
