package com.mahdi.sandogh.model.fund.service;

import com.mahdi.sandogh.model.account.Account;
import com.mahdi.sandogh.model.account.service.AccountService;
import com.mahdi.sandogh.model.fund.Fund;
import com.mahdi.sandogh.model.fund.dto.FundDto;
import com.mahdi.sandogh.model.fund.dto.FundForm;
import com.mahdi.sandogh.model.fund.dto.FundResponse;
import com.mahdi.sandogh.model.fund.dto.ListFundDto;
import com.mahdi.sandogh.model.fund.repository.FundRepo;
import com.mahdi.sandogh.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by mahdi
 * DateTime: ۱۲/۰۸/۲۰۲۰ - 17:35
 * http://github.com/mahdihp
 * http://gitlab.com/mahdihp
 */

@Service
public class FundService {

    @Autowired
    private FundRepo fundRepo;
    @Autowired
    private AccountService accountService;

    public FundResponse create(FundForm form) {
        List<Fund> list = fundRepo.findAllByDisplayNameAndDeleted(form.getDisplayName(), false);
        if (list != null && list.size() == 0) {
            Fund fund = new Fund();
            fund.setDisplayName(form.getDisplayName());
            fund.setCreateBy(form.getCreateBy());
            fund.setDescription(form.getDescription());
            fundRepo.save(fund);
            return FundResponse.Builder.aFundResponse()
                    .withStatus(200)
                    .withMessage(AppConstants.KEY_CREATE_FUND)
                    .build();
        }
        return FundResponse.Builder.aFundResponse()
                .withStatus(201)
                .withMessage(AppConstants.KEY_REPEAT_FUND)
                .build();
    }

    public FundResponse update(FundForm form) {
        Optional<Fund> fund = fundRepo.findById(form.getId());
        if (fund.isPresent()) {
            fund.get().setDisplayName(form.getDisplayName());
            fund.get().setCreateBy(form.getCreateBy());
            fund.get().setDescription(form.getDescription());
            fundRepo.save(fund.get());
            return FundResponse.Builder.aFundResponse()
                    .withStatus(200)
                    .withMessage(AppConstants.KEY_UPDATE_FUND)
                    .build();
        }
        return FundResponse.Builder.aFundResponse()
                .withStatus(201)
                .withMessage(AppConstants.KEY_NOT_FOUND_FUND)
                .build();
    }

    public Optional<ListFundDto> findFundDtoById(Integer id) {
        Optional<Fund> fund = fundRepo.findById(id);
        if (fund.isPresent()) {
            ListFundDto fundDto = new ListFundDto();
            fundDto.setStatus(HttpStatus.OK.value());
            fundDto.setMessage(AppConstants.KEY_SUCESSE);
            FundDto fundDto1 = new FundDto();
            fundDto1.setDisplayName(fund.get().getDisplayName());
            fundDto1.setCreateBy(fund.get().getCreateBy());
            fundDto1.setDescription(fund.get().getDescription());

            fundDto.setData(Arrays.asList(fundDto1));
            return Optional.ofNullable(fundDto);
        }
        return Optional.empty();
    }

    public Optional<Fund> findFundById(Integer id) {
        Optional<Fund> fund = fundRepo.findById(id);
        if (fund.isPresent())
            return fund;
        else
            return Optional.empty();
    }

    public Optional<ListFundDto> findAllFund() {
        List<Fund> list = fundRepo.findAllByDeleted(false);
        if (list != null && list.size() == 0) {
            ListFundDto llDTO = new ListFundDto();
            llDTO.setStatus(HttpStatus.OK.value());
            llDTO.setMessage(AppConstants.KEY_SUCESSE);
            List<FundDto> dtoList = new ArrayList<>();
            for (Fund fund : list) {
                FundDto fundDto = new FundDto();
                fundDto.setDisplayName(fund.getDisplayName());
                fundDto.setCreateBy(fund.getCreateBy());
                fundDto.setDescription(fund.getDescription());
                dtoList.add(fundDto);
            }
            llDTO.setData(dtoList);
            return Optional.ofNullable(llDTO);
        }
        return Optional.empty();
    }

    public FundResponse addAccountToFund(String accountNumber, Integer fundId) {
        Optional<Account> byAccountNumber = accountService.findByAccountNumber(accountNumber);
        Optional<Fund> fund = fundRepo.findById(fundId);
        if (byAccountNumber.isPresent()) {
            for (Fund fund1 : byAccountNumber.get().getFunds()) {
                if (fund1.getId().equals(fundId)) {
                    return FundResponse.Builder.aFundResponse()
                            .withStatus(201)
                            .withMessage(AppConstants.KEY_ALREADY_ADD_FUND)
                            .build();
                }
            }
            Set<Account> funds = new HashSet<>();
            funds.add(byAccountNumber.get());
            fund.get().setAccounts(funds);
            fundRepo.save(fund.get());
            return FundResponse.Builder.aFundResponse()
                    .withStatus(200)
                    .withMessage(AppConstants.KEY_ADD_TO_FUND)
                    .build();
        }
        return FundResponse.Builder.aFundResponse()
                .withStatus(201)
                .withMessage(AppConstants.KEY_ALREADY_ADD_FUND)
                .build();
    }

    public FundResponse removeAccountAsFund(String accountNumber, Integer fundId) {
        Optional<Account> byAccountNumber = accountService.findByAccountNumber(accountNumber);
        if (byAccountNumber.isPresent()) {
            for (Fund fund1 : byAccountNumber.get().getFunds()) {
                if (fund1.getId().equals(fundId)) {
                    byAccountNumber.get().getFunds().remove(fund1);
                    accountService.saveAccount(byAccountNumber.get());
                    return FundResponse.Builder.aFundResponse()
                            .withStatus(200)
                            .withMessage(AppConstants.KEY_REMOVE_AS_FUND)
                            .build();
                }
            }

        }
        return FundResponse.Builder.aFundResponse()
                .withStatus(201)
                .withMessage(AppConstants.KEY_NOT_FOUND_FUND)
                .build();
    }

    public FundResponse remove(Integer id) {
        Optional<Fund> fund = fundRepo.findById(id);
        if (fund.isPresent()) {
            fund.get().setDeleted(true);
            fundRepo.save(fund.get());
            return FundResponse.Builder.aFundResponse()
                    .withStatus(200)
                    .withMessage(AppConstants.KEY_REMOVE_FUND)
                    .build();
        }
        return FundResponse.Builder.aFundResponse()
                .withStatus(201)
                .withMessage(AppConstants.KEY_NOT_FOUND_FUND)
                .build();
    }

    public FundResponse recovery(Integer id) {
        Optional<Fund> fund = fundRepo.findById(id);
        if (fund.isPresent()) {
            fund.get().setDeleted(false);
            fundRepo.save(fund.get());
            return FundResponse.Builder.aFundResponse()
                    .withStatus(200)
                    .withMessage(AppConstants.KEY_RECOVERY_FUND)
                    .build();
        }
        return FundResponse.Builder.aFundResponse()
                .withStatus(201)
                .withMessage(AppConstants.KEY_NOT_FOUND_FUND)
                .build();
    }

    public Optional<List<Account>> findAllAccountByFundId(Integer fundId) {
        List<Account> list = fundRepo.findAllByAccountsAndIdEquals(fundId);
        if (list != null)
            return Optional.ofNullable(list);
        else
            return Optional.empty();
    }

    public Optional<List<Account>> findAllAccountByNotFundId(Integer fundId) {
        List<Account> list = fundRepo.findAllByAccountsIsNotId(fundId);
        if (list != null)
            return Optional.ofNullable(list);
        else
            return Optional.empty();
    }
}
