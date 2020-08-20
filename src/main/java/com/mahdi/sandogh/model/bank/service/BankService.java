package com.mahdi.sandogh.model.bank.service;

import com.mahdi.sandogh.model.account.dto.AccountDto;
import com.mahdi.sandogh.model.bank.Bank;
import com.mahdi.sandogh.model.bank.dto.BankDto;
import com.mahdi.sandogh.model.bank.dto.BankForm;
import com.mahdi.sandogh.model.bank.dto.BankResponse;
import com.mahdi.sandogh.model.bank.dto.ListBankDto;
import com.mahdi.sandogh.model.bank.repository.BankRepo;
import com.mahdi.sandogh.model.fund.Fund;
import com.mahdi.sandogh.model.fund.service.FundService;
import com.mahdi.sandogh.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by: mahdi
 * DateTime: ۱۵/۰۸/۲۰۲۰ - 19:24:41
 * Email: mahdip.devsc@gmail.com
 * http://github.com/mahdihp & http://gitlab.com/mahdihp
 */

@Service
public class BankService {

    @Autowired
    private BankRepo bankRepo;
    @Autowired
    private FundService fundService;

    public BankResponse create(BankForm form) {
        Boolean aBoolean = bankRepo.existsByDisplayNameAndAccountNumber(form.getDisplayName(), form.getAccountNumber());
        if (aBoolean == false) {
            Optional<Fund> fund = fundService.findFundById(form.getFundId());
            if (fund.isPresent()) {
                Bank bank = new Bank();
                bank.setDisplayName(form.getDisplayName());
                bank.setAmount(form.getAmount());
                bank.setSheba(form.getSheba());
                bank.setAccountNumber(form.getAccountNumber());
                bank.setCustomerNumber(form.getCustomerNumber());
                bank.setUsername(form.getUsername());
                bank.setPassword(form.getPassword());
                bank.setCardNumber(form.getCardNumber());
                bank.setDescription(form.getDescription());
                bank.setFund(fund.get());
                bankRepo.save(bank);
                return BankResponse.Builder.aBankResponse()
                        .withStatus(200)
                        .withMessage(AppConstants.KEY_ADD_BANK)
                        .build();
            } else {
                return BankResponse.Builder.aBankResponse()
                        .withStatus(201)
                        .withMessage(AppConstants.KEY_NOT_FOUND_FUND)
                        .build();
            }
        }
        return BankResponse.Builder.aBankResponse()
                .withStatus(201)
                .withMessage(AppConstants.KEY_ALREADY_ADD_BANK)
                .build();
    }

    public BankResponse update(BankForm form) {
        Optional<Bank> bank = bankRepo.findById(form.getId());
        if (bank.isPresent()) {
            Optional<Fund> fund = fundService.findFundById(form.getFundId());
            if (fund.isPresent()) {
                bank.get().setDisplayName(form.getDisplayName());
                bank.get().setAmount(form.getAmount());
                bank.get().setSheba(form.getSheba());
                bank.get().setAccountNumber(form.getAccountNumber());
                bank.get().setCustomerNumber(form.getCustomerNumber());
                bank.get().setUsername(form.getUsername());
                bank.get().setPassword(form.getPassword());
                bank.get().setCardNumber(form.getCardNumber());
                bank.get().setDescription(form.getDescription());
                bank.get().setFund(fund.get());
                bankRepo.save(bank.get());
                return BankResponse.Builder.aBankResponse()
                        .withStatus(200)
                        .withMessage(AppConstants.KEY_ADD_BANK)
                        .build();
            } else {
                return BankResponse.Builder.aBankResponse()
                        .withStatus(201)
                        .withMessage(AppConstants.KEY_NOT_FOUND_FUND)
                        .build();
            }
        }
        return BankResponse.Builder.aBankResponse()
                .withStatus(201)
                .withMessage(AppConstants.KEY_NOT_FOUND_BANK)
                .build();
    }

    public Optional<ListBankDto> findById(Integer id) {
        Optional<Bank> bankRef = bankRepo.findById(id);
        if (bankRef.isPresent()) {
            ListBankDto listBankDto = new ListBankDto();

            listBankDto.setStatus(HttpStatus.OK.value());
            listBankDto.setMessage(AppConstants.KEY_SUCESSE);

            BankDto bank = new BankDto();
            bank.setDisplayName(bankRef.get().getDisplayName());
            bank.setAmount(bankRef.get().getAmount());
            bank.setSheba(bankRef.get().getSheba());
            bank.setAccountNumber(bankRef.get().getAccountNumber());
            bank.setCustomerNumber(bankRef.get().getCustomerNumber());
            bank.setUsername(bankRef.get().getUsername());
            bank.setPassword(bankRef.get().getPassword());
            bank.setCardNumber(bankRef.get().getCardNumber());
            bank.setDescription(bankRef.get().getDescription());
            bank.setFundId(bankRef.get().getFund().getId());

            listBankDto.setData(Arrays.asList(bank));
            return Optional.ofNullable(listBankDto);
        }
        return Optional.empty();
    }
    public Optional<ListBankDto> findAllBankByFund(Integer fundId) {
        List<Bank> list = bankRepo.findAllByFundId(fundId);
        if (list != null) {
            ListBankDto listBankDto = new ListBankDto();

            listBankDto.setStatus(HttpStatus.OK.value());
            listBankDto.setMessage(AppConstants.KEY_SUCESSE);

            List<BankDto> dtoList = new ArrayList<>();
            for (Bank bankRef : list) {
                BankDto bank = new BankDto();
                bank.setDisplayName(bankRef.getDisplayName());
                bank.setAmount(bankRef.getAmount());
                bank.setSheba(bankRef.getSheba());
                bank.setAccountNumber(bankRef.getAccountNumber());
                bank.setCustomerNumber(bankRef.getCustomerNumber());
                bank.setUsername(bankRef.getUsername());
                bank.setPassword(bankRef.getPassword());
                bank.setCardNumber(bankRef.getCardNumber());
                bank.setDescription(bankRef.getDescription());
                bank.setFundId(bankRef.getFund().getId());
                dtoList.add(bank);
            }
            listBankDto.setData(dtoList);
            return Optional.ofNullable(listBankDto);
        }
        return Optional.empty();
    }
    public Optional<ListBankDto> findAllBank() {
        List<Bank> list = bankRepo.findAll();
        if (list != null) {
            ListBankDto listBankDto = new ListBankDto();

            listBankDto.setStatus(HttpStatus.OK.value());
            listBankDto.setMessage(AppConstants.KEY_SUCESSE);

            List<BankDto> dtoList = new ArrayList<>();
            for (Bank bankRef : list) {
                BankDto bank = new BankDto();
                bank.setDisplayName(bankRef.getDisplayName());
                bank.setAmount(bankRef.getAmount());
                bank.setSheba(bankRef.getSheba());
                bank.setAccountNumber(bankRef.getAccountNumber());
                bank.setCustomerNumber(bankRef.getCustomerNumber());
                bank.setUsername(bankRef.getUsername());
                bank.setPassword(bankRef.getPassword());
                bank.setCardNumber(bankRef.getCardNumber());
                bank.setDescription(bankRef.getDescription());
                bank.setFundId(bankRef.getFund().getId());
                dtoList.add(bank);
            }
            listBankDto.setData(dtoList);
            return Optional.ofNullable(listBankDto);
        }
        return Optional.empty();
    }

    public BankResponse remove(Integer id){
        Optional<Bank> bankRef = bankRepo.findById(id);
        if (bankRef.isPresent()){
            bankRepo.delete(bankRef.get());
            return BankResponse.Builder.aBankResponse()
                    .withStatus(200)
                    .withMessage(AppConstants.KEY_REMOVE_BANK)
                    .build();
        }
        return BankResponse.Builder.aBankResponse()
                .withStatus(201)
                .withMessage(AppConstants.KEY_NOT_FOUND_BANK)
                .build();
    }
}
