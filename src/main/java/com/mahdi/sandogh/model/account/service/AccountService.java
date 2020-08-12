package com.mahdi.sandogh.model.account.service;

import com.mahdi.sandogh.model.account.Account;
import com.mahdi.sandogh.model.account.dto.AccountDto;
import com.mahdi.sandogh.model.account.dto.AccountForm;
import com.mahdi.sandogh.model.account.dto.ListAccountDto;
import com.mahdi.sandogh.model.account.repository.AccountRepo;
import com.mahdi.sandogh.utils.AppConstants;
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
    private AccountRepo accountRepo;

    @Value("${sandogh.app.accountNumber}")
    private long accountNumber;


    public UUID create(AccountForm accountForm) {
        Boolean exists = accountRepo.existsByNationalCode(accountForm.getNationalCode());
        UUID uid = UUID.randomUUID();
        if (!exists) {
            Account account = new Account();
//            account.setUid(uid);
            Long accountNumber = accountRepo.findMaxAccountNumber();
            if (accountNumber != null) {

                accountNumber++;
//                int years = DataUtil.generateNumericRandomAccountNumber();
//                account.setAccountNumber(String.valueOf(accountNumber) + years);
            } else {
//                int years = DataUtil.generateNumericRandomAccountNumber();
//                account.setAccountNumber(String.valueOf(this.accountNumber) + years);
            }
            account.setActive(true);
            account.setFirstName(accountForm.getFirstName());
            account.setLastName(accountForm.getLastName());
            account.setFatherName(accountForm.getFatherName());
            account.setMobileNumber(accountForm.getMobileNumber());
            account.setNationalCode(accountForm.getNationalCode());
            account.setCity(accountForm.getCity());
            account.setAdderss(accountForm.getAdderss());
//            account.setCreationDate(System.currentTimeMillis());
            accountRepo.save(account);
            return uid;
        }
        return null;
    }

    public boolean update(AccountForm accountForm) {
        Optional<Account> account = accountRepo.findById(accountForm.getAccountId());
        if (account.isPresent()) {
            account.get().setFirstName(accountForm.getFirstName());
            account.get().setLastName(accountForm.getLastName());
            account.get().setFatherName(accountForm.getFatherName());
            account.get().setMobileNumber(accountForm.getMobileNumber());
            account.get().setNationalCode(accountForm.getNationalCode());
            account.get().setCity(accountForm.getCity());
            account.get().setAdderss(accountForm.getAdderss());
//            account.get().setModificationDate(System.currentTimeMillis());
//            account.get().setModificationDate(System.currentTimeMillis());
            accountRepo.save(account.get());
            return true;
        }
        return false;
    }

    public Optional<Account> findById(Long id) {
        Optional<Account> account = accountRepo.findById(id);
        if (account.isPresent())
            return Optional.ofNullable(account.get());
        else
            return Optional.empty();
    }

    public Optional<AccountDto> findDTOByUid(int queryType, String uid) {
        Optional<Account> account = Optional.empty();
        switch (queryType) {
            case 1:
                account = accountRepo.findById(Long.valueOf(uid));
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
            AccountDto accountDTO = new AccountDto();
            accountDTO.setStatus(HttpStatus.OK.value());
            accountDTO.setMessage(AppConstants.KEY_SUCESSE);
//            accountDTO.setAccountId(account.get().getUid().toString());
            accountDTO.setActive(account.get().isActive());
            accountDTO.setFirstName(account.get().getFirstName());
            accountDTO.setLastName(account.get().getLastName());
            accountDTO.setAccountNumber(account.get().getAccountNumber());
            accountDTO.setFatherName(account.get().getFatherName());
            accountDTO.setMobileNumber(account.get().getMobileNumber());
            accountDTO.setNationalCode(account.get().getNationalCode());
            accountDTO.setCity(account.get().getCity());
            accountDTO.setAdderss(account.get().getAdderss());
//            accountDTO.setCreationDate(account.get().getCreationDate());
            return Optional.ofNullable(accountDTO);
        }
        return Optional.empty();
    }

    public Optional<ListAccountDto> findAllDTO() {
        List<Account> list = accountRepo.findAll();
        if (list != null) {
            ListAccountDto laDTO = new ListAccountDto();
            laDTO.setStatus(HttpStatus.OK.value());
            laDTO.setMessage(AppConstants.KEY_SUCESSE);
            List<AccountDto> dtoList = new ArrayList<>();
            for (Account account : list) {
                AccountDto accountDTO = new AccountDto();
//                accountDTO.setAccountId(account.getUid().toString());
                accountDTO.setActive(account.isActive());
                accountDTO.setFirstName(account.getFirstName());
                accountDTO.setAccountNumber(account.getAccountNumber());
                accountDTO.setLastName(account.getLastName());
                accountDTO.setFatherName(account.getFatherName());
                accountDTO.setMobileNumber(account.getMobileNumber());
                accountDTO.setNationalCode(account.getNationalCode());
                accountDTO.setCity(account.getCity());
                accountDTO.setAdderss(account.getAdderss());
//                accountDTO.setCreationDate(account.getCreationDate());
                dtoList.add(accountDTO);
            }
            laDTO.setData(dtoList);
            return Optional.ofNullable(laDTO);
        }
        return Optional.empty();
    }

    public Optional<ListAccountDto> findAllDTO(String firstName, String lastName) {
        Optional<List<Account>> list = accountRepo.findAllByFirstNameLikeOrLastNameLike(firstName, lastName);
        if (list.isPresent()) {
            ListAccountDto laDTO = new ListAccountDto();
            laDTO.setStatus(HttpStatus.OK.value());
            laDTO.setMessage(AppConstants.KEY_SUCESSE);
            List<AccountDto> dtoList = new ArrayList<>();
            for (Account account : list.get()) {
                AccountDto accountDTO = new AccountDto();
//                accountDTO.setAccountId(account.getUid().toString());
                accountDTO.setActive(account.isActive());
                accountDTO.setFirstName(account.getFirstName());
                accountDTO.setAccountNumber(account.getAccountNumber());
                accountDTO.setLastName(account.getLastName());
                accountDTO.setFatherName(account.getFatherName());
                accountDTO.setMobileNumber(account.getMobileNumber());
                accountDTO.setNationalCode(account.getNationalCode());
                accountDTO.setCity(account.getCity());
                accountDTO.setAdderss(account.getAdderss());
//                accountDTO.setCreationDate(account.getCreationDate());
                dtoList.add(accountDTO);
            }
            laDTO.setData(dtoList);
            return Optional.ofNullable(laDTO);
        }
        return Optional.empty();
    }
}
