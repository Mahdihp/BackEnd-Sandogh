package com.mahdi.sandogh.service;

import com.mahdi.sandogh.model.dto.account.AccountDTO;
import com.mahdi.sandogh.model.dto.account.AccountForm;
import com.mahdi.sandogh.model.dto.account.ListAccountDTO;
import com.mahdi.sandogh.model.sandogh.Account;
import com.mahdi.sandogh.repository.AccountRepo;
import com.mahdi.sandogh.utils.Constants;
import com.mahdi.sandogh.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    AccountRepo accountRepo;

    @Value("${mostashar.app.accountNumber}")
    private String accountNumber;


    public boolean createAccount(AccountForm accountForm) {
        Boolean exists = accountRepo.existsByNationalCode(accountForm.getNationalCode());
        if (!exists) {
            Account account = new Account();
            account.setUid(UUID.randomUUID());
            Long accountNumber = accountRepo.findMaxAccountNumber();
            if (accountNumber == null) {

               int years=  DataUtil.generateNumericRandomAccountNumber();
                account.setAccountNumber(String.valueOf(accountNumber + years));
            }else{
                accountNumber++;
                account.setAccountNumber(String.valueOf(accountNumber));

            }


            account.setActive(true);
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
            account.get().setActive(accountForm.isActive());
            account.get().setModificationDate(System.currentTimeMillis());
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

    public Optional<AccountDTO> findAccountDTOByUid(int queryType, String uid) {
        Optional<Account> account = Optional.empty();
        switch (queryType) {
            case 1:
                account = accountRepo.findByUid(UUID.fromString(uid));
                break;
            case 2:
                account = accountRepo.findByAccountNumber(uid);
                break;
            case 3:
                account = accountRepo.findByMobileNumber(uid);
                break;
            case 4:
                account = accountRepo.findByNationalCode(uid);
                break;

        }
        if (account.isPresent()) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setStatus(HttpStatus.OK.value());
            accountDTO.setMessage(Constants.KEY_SUCESSE);
            accountDTO.setAccountId(account.get().getUid().toString());
            accountDTO.setActive(account.get().isActive());
            accountDTO.setFirstName(account.get().getFirstName());
            accountDTO.setLastName(account.get().getLastName());
            accountDTO.setAccountNumber(account.get().getAccountNumber());
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

    public Optional<ListAccountDTO> findAllDTOByUid(String firstName, String lastName) {
        Optional<List<Account>> list = accountRepo.findAllByFirstNameLikeOrLastNameLike(firstName, lastName);
        if (list.isPresent()) {
            ListAccountDTO laDTO = new ListAccountDTO();
            laDTO.setStatus(HttpStatus.OK.value());
            laDTO.setMessage(Constants.KEY_SUCESSE);
            List<AccountDTO> dtoList = new ArrayList<>();
            for (Account account : list.get()) {
                AccountDTO accountDTO = new AccountDTO();

                accountDTO.setAccountId(account.getUid().toString());
                accountDTO.setActive(account.isActive());
                accountDTO.setFirstName(account.getFirstName());
                accountDTO.setAccountNumber(account.getAccountNumber());
                accountDTO.setLastName(account.getLastName());
                accountDTO.setFatherName(account.getFatherName());
                accountDTO.setMobileNumber(account.getMobileNumber());
                accountDTO.setNationalCode(account.getNationalCode());
                accountDTO.setCity(account.getCity());
                accountDTO.setAdderss(account.getAdderss());
                accountDTO.setCreationDate(account.getCreationDate());
                dtoList.add(accountDTO);
            }
            laDTO.setData(dtoList);
            return Optional.ofNullable(laDTO);
        }
        return Optional.empty();
    }
}
