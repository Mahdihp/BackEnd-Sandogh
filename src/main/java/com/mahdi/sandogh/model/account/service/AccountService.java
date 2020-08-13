package com.mahdi.sandogh.model.account.service;

import com.mahdi.sandogh.model.account.Account;
import com.mahdi.sandogh.model.account.dto.AccountDto;
import com.mahdi.sandogh.model.account.dto.AccountForm;
import com.mahdi.sandogh.model.account.dto.AccountReponse;
import com.mahdi.sandogh.model.account.dto.ListAccountDto;
import com.mahdi.sandogh.model.account.repository.AccountRepo;
import com.mahdi.sandogh.model.fund.Fund;
import com.mahdi.sandogh.model.fund.service.FundService;
import com.mahdi.sandogh.utils.AppConstants;
import com.mahdi.sandogh.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Value("${sandogh.app.accountNumber}")
    private long accountNumber;
    @Autowired
    private FundService fundService;

    public AccountReponse create(AccountForm form) {
        Optional<Fund> fund = fundService.findFundById(form.getFundid());
        Optional<Account> byNationalCode = accountRepo.findByNationalCode(form.getNationalCode());
        if (fund.isPresent() && !byNationalCode.isPresent()) {
            Account account = new Account();
            Long accountNumber = accountRepo.findMaxAccountNumber();
            if (accountNumber != null) {
                accountNumber++;
                int years = DataUtil.generateNumericRandomAccountNumber();
                account.setAccountNumber(String.valueOf(accountNumber) + years);
            } else {
                int years = DataUtil.generateNumericRandomAccountNumber();
                account.setAccountNumber(String.valueOf(this.accountNumber) + years);
            }
            account.setActive(true);
            account.setFirstName(form.getFirstName());
            account.setLastName(form.getLastName());
            account.setFatherName(form.getFatherName());
            account.setMobileNumber(form.getMobileNumber());
            account.setNationalCode(form.getNationalCode());
            account.setCity(form.getCity());
            account.setAdderss(form.getAdderss());
            Set<Fund> funds = new HashSet<>();
            funds.add(fund.get());
            account.setFunds(funds);
            Account save = accountRepo.save(account);
            return AccountReponse.Builder.asAccountReponse()
                    .withAccountNumber(save.getAccountNumber())
                    .withStatus(200)
                    .withMessage(AppConstants.KEY_CREATE_ACCOUNT)
                    .build();
        }
        return AccountReponse.Builder.asAccountReponse()
                .withActive(true)
                .withStatus(201)
                .withMessage(AppConstants.KEY_REPEAT_ACCOUNT)
                .build();
    }

    public AccountReponse update(AccountForm accountForm) {
        Optional<Account> account = accountRepo.findByAccountNumber(accountForm.getAccountNumber());
        if (account.isPresent()) {
            account.get().setFirstName(accountForm.getFirstName());
            account.get().setLastName(accountForm.getLastName());
            account.get().setFatherName(accountForm.getFatherName());
            account.get().setMobileNumber(accountForm.getMobileNumber());
            account.get().setNationalCode(accountForm.getNationalCode());
            account.get().setCity(accountForm.getCity());
            account.get().setAdderss(accountForm.getAdderss());
            accountRepo.save(account.get());
            return AccountReponse.Builder.asAccountReponse()
                    .withStatus(200)
                    .withMessage(AppConstants.KEY_UPDATE_ACCOUNT)
                    .build();
        }
        return AccountReponse.Builder.asAccountReponse()
                .withStatus(201)
                .withMessage(AppConstants.KEY_NOT_FOUND_ACCOUNT)
                .build();
    }

    public Optional<Account> findById(Long id) {
        Optional<Account> account = accountRepo.findById(id);
        if (account.isPresent())
            return Optional.ofNullable(account.get());
        else
            return Optional.empty();
    }

    public Optional<Account> findByAccountNumber(String accountNumber) {
        Optional<Account> account = accountRepo.findByAccountNumber(accountNumber);
        if (account.isPresent())
            return Optional.ofNullable(account.get());
        else
            return Optional.empty();
    }

    public Optional<ListAccountDto> findDtoByFields(int queryType, String id) {
        Optional<Account> account = Optional.empty();
        switch (queryType) {
            case 1:
                account = accountRepo.findByAccountNumber(id);
                break;
            case 2:
                account = accountRepo.findByMobileNumber(id);
                break;
            case 3:
                account = accountRepo.findByNationalCode(id);
                break;
        }
        if (account.isPresent()) {
            ListAccountDto listAccountDto = new ListAccountDto();
            AccountDto accountDTO = new AccountDto();
            listAccountDto.setStatus(HttpStatus.OK.value());
            listAccountDto.setMessage(AppConstants.KEY_SUCESSE);

            accountDTO.setActive(account.get().isActive());
            accountDTO.setFirstName(account.get().getFirstName());
            accountDTO.setLastName(account.get().getLastName());
            accountDTO.setAccountNumber(account.get().getAccountNumber());
            accountDTO.setFatherName(account.get().getFatherName());
            accountDTO.setMobileNumber(account.get().getMobileNumber());
            accountDTO.setNationalCode(account.get().getNationalCode());
            accountDTO.setCity(account.get().getCity());
            accountDTO.setAdderss(account.get().getAdderss());

            listAccountDto.setData(Arrays.asList(accountDTO));
            return Optional.of(listAccountDto);
        }
        return Optional.empty();
    }

    public Optional<ListAccountDto> findAllDto() {
        List<Account> list = accountRepo.findAll();
        if (list != null) {
            ListAccountDto laDTO = new ListAccountDto();
            laDTO.setStatus(HttpStatus.OK.value());
            laDTO.setMessage(AppConstants.KEY_SUCESSE);
            List<AccountDto> dtoList = new ArrayList<>();
            for (Account account : list) {
                AccountDto accountDTO = new AccountDto();
                accountDTO.setActive(account.isActive());
                accountDTO.setFirstName(account.getFirstName());
                accountDTO.setAccountNumber(account.getAccountNumber());
                accountDTO.setLastName(account.getLastName());
                accountDTO.setFatherName(account.getFatherName());
                accountDTO.setMobileNumber(account.getMobileNumber());
                accountDTO.setNationalCode(account.getNationalCode());
                accountDTO.setCity(account.getCity());
                accountDTO.setAdderss(account.getAdderss());
                dtoList.add(accountDTO);
            }
            laDTO.setData(dtoList);
            return Optional.ofNullable(laDTO);
        }
        return Optional.empty();
    }

    public Optional<ListAccountDto> findAllDto(String firstName, String lastName) {
        Optional<List<Account>> list = accountRepo.findAllByFirstNameLikeOrLastNameLike(firstName, lastName);
        if (list.isPresent()) {
            ListAccountDto laDTO = new ListAccountDto();
            laDTO.setStatus(HttpStatus.OK.value());
            laDTO.setMessage(AppConstants.KEY_SUCESSE);
            List<AccountDto> dtoList = new ArrayList<>();
            for (Account account : list.get()) {
                AccountDto accountDTO = new AccountDto();
                accountDTO.setActive(account.isActive());
                accountDTO.setFirstName(account.getFirstName());
                accountDTO.setAccountNumber(account.getAccountNumber());
                accountDTO.setLastName(account.getLastName());
                accountDTO.setFatherName(account.getFatherName());
                accountDTO.setMobileNumber(account.getMobileNumber());
                accountDTO.setNationalCode(account.getNationalCode());
                accountDTO.setCity(account.getCity());
                accountDTO.setAdderss(account.getAdderss());
                dtoList.add(accountDTO);
            }
            laDTO.setData(dtoList);
            return Optional.ofNullable(laDTO);
        }
        return Optional.empty();
    }

    public AccountReponse remove(String accountNumber) {
        Optional<Account> account = accountRepo.findByAccountNumber(accountNumber);
        if (account.isPresent()) {
            account.get().setDeleted(true);
            accountRepo.save(account.get());
            return AccountReponse.Builder.asAccountReponse()
                    .withStatus(200)
                    .withMessage(AppConstants.KEY_REMOVE_ACCOUNT)
                    .build();
        }
        return AccountReponse.Builder.asAccountReponse()
                .withStatus(201)
                .withMessage(AppConstants.KEY_NOT_FOUND_ACCOUNT)
                .build();
    }

    public AccountReponse recovery(String accountNumber) {
        Optional<Account> account = accountRepo.findByAccountNumber(accountNumber);
        if (account.isPresent()) {
            account.get().setDeleted(false);
            accountRepo.save(account.get());
            return AccountReponse.Builder.asAccountReponse()
                    .withStatus(200)
                    .withMessage(AppConstants.KEY_RECOVERY_ACCOUNT)
                    .build();
        }
        return AccountReponse.Builder.asAccountReponse()
                .withStatus(201)
                .withMessage(AppConstants.KEY_NOT_FOUND_ACCOUNT)
                .build();
    }

    public AccountReponse deactivate(String accountNumber) {
        Optional<Account> account = accountRepo.findByAccountNumber(accountNumber);
        if (account.isPresent()) {
            account.get().setActive(false);
            accountRepo.save(account.get());
            return AccountReponse.Builder.asAccountReponse()
                    .withStatus(200)
                    .withMessage(AppConstants.KEY_DEACTIVATE_ACCOUNT)
                    .build();
        }
        return AccountReponse.Builder.asAccountReponse()
                .withStatus(201)
                .withMessage(AppConstants.KEY_NOT_FOUND_ACCOUNT)
                .build();
    }

    public AccountReponse activate(String accountNumber) {
        Optional<Account> account = accountRepo.findByAccountNumber(accountNumber);
        if (account.isPresent()) {
            account.get().setActive(true);
            accountRepo.save(account.get());
            return AccountReponse.Builder.asAccountReponse()
                    .withStatus(200)
                    .withMessage(AppConstants.KEY_ACTIVATE_ACCOUNT)
                    .build();
        }
        return AccountReponse.Builder.asAccountReponse()
                .withStatus(201)
                .withMessage(AppConstants.KEY_NOT_FOUND_ACCOUNT)
                .build();
    }
}
