package com.mahdi.sandogh.model.monthly.service;

import com.mahdi.sandogh.model.account.Account;
import com.mahdi.sandogh.model.account.service.AccountService;
import com.mahdi.sandogh.model.monthly.Monthly;
import com.mahdi.sandogh.model.monthly.dto.ListMonthlyDTO;
import com.mahdi.sandogh.model.monthly.dto.MonthlyDTO;
import com.mahdi.sandogh.model.monthly.dto.MonthlyForm;
import com.mahdi.sandogh.model.monthly.repository.MonthlyRepo;
import com.mahdi.sandogh.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public boolean create(MonthlyForm monthlyForm) {
        Optional<Account> account = accountService.findById(monthlyForm.getAccountId());
        if (account.isPresent()) {
            Monthly monthly = new Monthly();
//            monthly.setUid(UUID.randomUUID());
            monthly.setAmountPerMonth(monthlyForm.getAmountPerMonth());
            monthly.setAccount(account.get());
            monthlyRepo.save(monthly);
            return true;
        }
        return false;
    }

    public boolean update(MonthlyForm monthlyForm) {
        Optional<Monthly> monthly = monthlyRepo.findById(monthlyForm.getMonthlyId());
        if (monthly.isPresent()) {
            monthly.get().setAmountPerMonth(monthlyForm.getAmountPerMonth());
            monthlyRepo.save(monthly.get());
            return true;
        }
        return false;
    }

    public Optional<Monthly> findById(Long monthlyId) {
        Optional<Monthly> monthly = monthlyRepo.findById((monthlyId));
        return monthly;
    }

    public Optional<MonthlyDTO> findDTOById(Long monthlyId) {
        Optional<Monthly> monthly = monthlyRepo.findById((monthlyId));
        if (monthly.isPresent()) {
            MonthlyDTO monthlyDTO = new MonthlyDTO();
            monthlyDTO.setStatus(HttpStatus.OK.value());
            monthlyDTO.setMessage(AppConstants.KEY_SUCESSE);

//            monthlyDTO.setMonthlyId(monthly.get().getUid().toString());
            monthlyDTO.setAmountPerMonth(monthly.get().getAmountPerMonth());
//            monthlyDTO.setCreationDate(monthly.get().getCreationDate());
//            monthlyDTO.setModificationDate(monthly.get().getModificationDate());
//            monthlyDTO.setAccountId(monthly.get().getAccount().getUid().toString());
            return Optional.ofNullable(monthlyDTO);
        }
        return Optional.empty();
    }

    public Optional<ListMonthlyDTO> findAllDTO(Long accountId) {
        Optional<List<Monthly>> list = monthlyRepo.findAllByAccountId(accountId);
        if (list.isPresent()) {
            ListMonthlyDTO lmDTO = new ListMonthlyDTO();
            lmDTO.setStatus(HttpStatus.OK.value());
            lmDTO.setMessage(AppConstants.KEY_SUCESSE);
            List<MonthlyDTO> dtoList = new ArrayList<>();
            for (Monthly monthly : list.get()) {
                MonthlyDTO monthlyDTO = new MonthlyDTO();

//                monthlyDTO.setMonthlyId(monthly.getUid().toString());
                monthlyDTO.setAmountPerMonth(monthly.getAmountPerMonth());
//                monthlyDTO.setCreationDate(monthly.getCreationDate());
//                monthlyDTO.setModificationDate(monthly.getModificationDate());
//                monthlyDTO.setAccountId(monthly.getAccount().getUid().toString());
                dtoList.add(monthlyDTO);
            }
            lmDTO.setData(dtoList);
            return Optional.ofNullable(lmDTO);
        }
        return Optional.empty();
    }
}