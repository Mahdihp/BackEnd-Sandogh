package com.mahdi.sandogh.model.monthly.service;

import com.mahdi.sandogh.model.account.Account;
import com.mahdi.sandogh.model.account.service.AccountService;
import com.mahdi.sandogh.model.fund.Fund;
import com.mahdi.sandogh.model.fund.service.FundService;
import com.mahdi.sandogh.model.monthly.Monthly;
import com.mahdi.sandogh.model.monthly.dto.ListMonthlyDto;
import com.mahdi.sandogh.model.monthly.dto.MonthlyDto;
import com.mahdi.sandogh.model.monthly.dto.MonthlyForm;
import com.mahdi.sandogh.model.monthly.dto.MonthlyResponse;
import com.mahdi.sandogh.model.monthly.repository.MonthlyRepo;
import com.mahdi.sandogh.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/18/19
 * Time: 21:14
 * https://github.com/mahdihp
 */


@Service
public class MonthlyService {

    @Autowired
    private MonthlyRepo monthlyRepo;
    @Autowired
    private AccountService accountService;
    @Autowired
    private FundService fundService;

    public MonthlyResponse create(MonthlyForm form) {
        Optional<Account> account = accountService.findByAccountNumber(form.getAccountNumber());
        Optional<Fund> fund = fundService.findFundById(form.getFundId());
        if (account.isPresent() && fund.isPresent()) {
            Monthly monthly = new Monthly();
            monthly.setAmountPerMonth(form.getAmountPerMonth());
            monthly.setAccount(account.get());
            monthly.setFund(fund.get());
            monthlyRepo.save(monthly);
            return MonthlyResponse.Builder.aMonthlyResponse()
                    .withStatus(200)
                    .withMessage(AppConstants.KEY_CREATE_MONTHLY)
                    .build();
        }
        return MonthlyResponse.Builder.aMonthlyResponse()
                .withStatus(201)
                .withMessage(AppConstants.KEY_NOT_FOUND_FUND + "\n" + " یا " +
                        AppConstants.KEY_NOT_FOUND_ACCOUNT)
                .build();
    }

    public MonthlyResponse update(MonthlyForm monthlyForm) {
        Optional<Monthly> monthly = monthlyRepo.findById(monthlyForm.getMonthlyId());
        if (monthly.isPresent()) {
            monthly.get().setAmountPerMonth(monthlyForm.getAmountPerMonth());
            monthlyRepo.save(monthly.get());
            return MonthlyResponse.Builder.aMonthlyResponse()
                    .withStatus(200)
                    .withMessage(AppConstants.KEY_UPDATE_MONTHLY)
                    .build();
        }
        return MonthlyResponse.Builder.aMonthlyResponse()
                .withStatus(201)
                .withMessage(AppConstants.KEY_NOT_FOUND_MONTHLY)
                .build();
    }

    public Optional<Monthly> findById(Long monthlyId) {
        Optional<Monthly> monthly = monthlyRepo.findById((monthlyId));
        return monthly;
    }

    public Optional<ListMonthlyDto> findDtoById(Long monthlyId) {
        Optional<Monthly> monthly = monthlyRepo.findById((monthlyId));
        if (monthly.isPresent()) {
            ListMonthlyDto lmDTO = new ListMonthlyDto();
            lmDTO.setStatus(HttpStatus.OK.value());
            lmDTO.setMessage(AppConstants.KEY_SUCESSE);
            List<MonthlyDto> dtoList = new ArrayList<>();

            MonthlyDto monthlyDTO = new MonthlyDto();
            monthlyDTO.setMonthlyId(monthly.get().getId());
            monthlyDTO.setAmountPerMonth(monthly.get().getAmountPerMonth());
            monthlyDTO.setCreationDate(monthly.get().getCreatedAt());
            monthlyDTO.setAccountNumber(monthly.get().getAccount().getAccountNumber());
            monthlyDTO.setFundId(monthly.get().getFund().getId());

            dtoList.add(monthlyDTO);
            lmDTO.setData(dtoList);

            return Optional.ofNullable(lmDTO);
        }
        return Optional.empty();
    }

    public Optional<ListMonthlyDto> findAllDTO(Long accountId) {
        Optional<List<Monthly>> list = monthlyRepo.findAllByAccountId(accountId);
        if (list.isPresent()) {
            ListMonthlyDto lmDTO = new ListMonthlyDto();
            lmDTO.setStatus(HttpStatus.OK.value());
            lmDTO.setMessage(AppConstants.KEY_SUCESSE);
            List<MonthlyDto> dtoList = new ArrayList<>();
            for (Monthly monthly : list.get()) {
                MonthlyDto monthlyDTO = new MonthlyDto();
                monthlyDTO.setMonthlyId(monthly.getId());
                monthlyDTO.setAmountPerMonth(monthly.getAmountPerMonth());
                monthlyDTO.setCreationDate(monthly.getCreatedAt());
                monthlyDTO.setAccountNumber(monthly.getAccount().getAccountNumber());
                monthlyDTO.setFundId(monthly.getFund().getId());
                dtoList.add(monthlyDTO);
            }
            lmDTO.setData(dtoList);
            return Optional.ofNullable(lmDTO);
        }
        return Optional.empty();
    }
}