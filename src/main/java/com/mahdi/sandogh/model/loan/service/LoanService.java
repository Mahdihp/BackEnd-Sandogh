package com.mahdi.sandogh.model.loan.service;


import com.mahdi.sandogh.model.account.Account;
import com.mahdi.sandogh.model.account.service.AccountService;
import com.mahdi.sandogh.model.fund.Fund;
import com.mahdi.sandogh.model.fund.service.FundService;
import com.mahdi.sandogh.model.loan.Loan;
import com.mahdi.sandogh.model.loan.dto.ListLoanDto;
import com.mahdi.sandogh.model.loan.dto.LoanDto;
import com.mahdi.sandogh.model.loan.dto.LoanForm;
import com.mahdi.sandogh.model.loan.dto.LoanResponse;
import com.mahdi.sandogh.model.loan.repository.LoanRepo;
import com.mahdi.sandogh.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepo loanRepo;

    @Autowired
    private AccountService accountService;

    @Autowired
    private FundService fundService;

    //  یه عضو چند بار وام می تونه بگیره
    // یک عضو چند بار در سال می تونه وام بگیره
    // یک عضو ایا می تونه چند بار وام از صندوق های مختلف وام بگیره؟
    public LoanResponse create(LoanForm form) {
        Optional<Fund> fund = fundService.findFundById(form.getFundId());
        Optional<Account> account = accountService.findById(form.getAccountId());

        if (account.isPresent() && fund.isPresent()) {
            Loan loan = new Loan();
            loan.setCountLoan(form.getCountLoan());
            loan.setCurrentLoanAmount(form.getCurrentLoanAmount());
            loan.setDateCurrentLoan(form.getDateCurrentLoan());
            loan.setCountInstallments(form.getCountInstallments());
            loan.setAmountPerInstallment(form.getAmountPerInstallment());
            loan.setDateFinishInstallment(form.getDateFinishInstallment());
            loan.setAccount(account.get());
            loan.setFund(fund.get());
            loanRepo.save(loan);
            return LoanResponse.Builder.aLoanResponse()
                    .withMessage(AppConstants.KEY_CREATE_LOAN)
                    .withStatus(200)
                    .build();
        }
        return LoanResponse.Builder.aLoanResponse()
                .withMessage(AppConstants.KEY_NOT_FOUND_FUND + "\n" + " یا " +
                        AppConstants.KEY_NOT_FOUND_ACCOUNT)
                .withStatus(201)
                .build();
    }

    public LoanResponse update(LoanForm form) {
        Optional<Fund> fund = fundService.findFundById(form.getFundId());
        Optional<Account> account = accountService.findById(form.getAccountId());
        Optional<Loan> loan = loanRepo.findById((form.getLoanId()));
        if (loan.isPresent() && account.isPresent() && fund.isPresent()) {
            loan.get().setCountLoan(form.getCountLoan());
            loan.get().setCurrentLoanAmount(form.getCurrentLoanAmount());
            loan.get().setDateCurrentLoan(form.getDateCurrentLoan());
            loan.get().setCountInstallments(form.getCountInstallments());
            loan.get().setAmountPerInstallment(form.getAmountPerInstallment());
            loan.get().setDateFinishInstallment(form.getDateFinishInstallment());
            loan.get().setAccount(account.get());
            loan.get().setFund(fund.get());
            loanRepo.save(loan.get());
            return LoanResponse.Builder.aLoanResponse()
                    .withMessage(AppConstants.KEY_UPDATE_LOAN)
                    .withStatus(200)
                    .build();
        }
        return LoanResponse.Builder.aLoanResponse()
                .withMessage(AppConstants.KEY_NOT_FOUND_LOAN + "\n" + " یا " +
                        AppConstants.KEY_NOT_FOUND_FUND + "\n" + " یا " +
                        AppConstants.KEY_NOT_FOUND_ACCOUNT)
                .withStatus(201)
                .build();
    }

    public Optional<ListLoanDto> findAllDto() {
        List<Loan> list = loanRepo.findAll();
        if (list != null) {
            ListLoanDto llDTO = new ListLoanDto();
            llDTO.setStatus(HttpStatus.OK.value());
            llDTO.setMessage(AppConstants.KEY_SUCESSE);
            List<LoanDto> dtoList = new ArrayList<>();
            for (Loan loan : list) {
                LoanDto loanDTO = new LoanDto();
                loanDTO.setLoanId(loan.getId());
                loanDTO.setCountLoan(loan.getCountLoan());
                loanDTO.setCurrentLoanAmount(loan.getCurrentLoanAmount());
                loanDTO.setDateCurrentLoan(loan.getDateCurrentLoan());
                loanDTO.setCountInstallments(loan.getCountInstallments());
                loanDTO.setAmountPerInstallment(loan.getAmountPerInstallment());
                loanDTO.setDateFinishInstallment(loan.getDateFinishInstallment());
                loanDTO.setAccountId(loan.getAccount().getId());
                loanDTO.setFundId(loan.getFund().getId());
                loanDTO.setCreatedAt(loan.getFund().getCreatedAt());
                dtoList.add(loanDTO);
            }
            llDTO.setData(dtoList);
            return Optional.ofNullable(llDTO);
        }
        return Optional.empty();
    }

    public Optional<Loan> findById(Integer loanId) {
        Optional<Loan> loan = loanRepo.findById((loanId));
        if (loan.isPresent())
            return Optional.ofNullable(loan.get());
        else
            return Optional.empty();
    }
    public Optional<ListLoanDto> findALlLoanAccount(String accountNumber) {
        Optional<Account> account = accountService.findByAccountNumber(accountNumber);
        if (account.isPresent()){
            ListLoanDto llDTO = new ListLoanDto();
            llDTO.setStatus(HttpStatus.OK.value());
            llDTO.setMessage(AppConstants.KEY_SUCESSE);
            List<LoanDto> dtoList = new ArrayList<>();
            for (Loan loan : account.get().getLoans()) {
                LoanDto loanDTO = new LoanDto();
                loanDTO.setLoanId(loan.getId());
                loanDTO.setCountLoan(loan.getCountLoan());
                loanDTO.setCurrentLoanAmount(loan.getCurrentLoanAmount());
                loanDTO.setDateCurrentLoan(loan.getDateCurrentLoan());
                loanDTO.setCountInstallments(loan.getCountInstallments());
                loanDTO.setAmountPerInstallment(loan.getAmountPerInstallment());
                loanDTO.setDateFinishInstallment(loan.getDateFinishInstallment());
                loanDTO.setAccountId(loan.getAccount().getId());
                loanDTO.setFundId(loan.getFund().getId());
                loanDTO.setCreatedAt(loan.getFund().getCreatedAt());
                dtoList.add(loanDTO);
            }
            llDTO.setData(dtoList);
            return Optional.ofNullable(llDTO);
        }
        return Optional.empty();
    }
    public Optional<ListLoanDto> findAllByFundId(Integer fundId) {
        Optional<Fund> list = fundService.findFundById(fundId);
        if (list.isPresent()) {
            ListLoanDto llDTO = new ListLoanDto();
            llDTO.setStatus(HttpStatus.OK.value());
            llDTO.setMessage(AppConstants.KEY_SUCESSE);
            List<LoanDto> dtoList = new ArrayList<>();

            for (Loan loan : list.get().getLoans()) {
                LoanDto loanDTO = new LoanDto();
                loanDTO.setLoanId(loan.getId());
                loanDTO.setCountLoan(loan.getCountLoan());
//                loanDTO.setSumLoan(loan.getSumLoan());
                loanDTO.setCurrentLoanAmount(loan.getCurrentLoanAmount());
                loanDTO.setDateCurrentLoan(loan.getDateCurrentLoan());
                loanDTO.setCountInstallments(loan.getCountInstallments());
                loanDTO.setAmountPerInstallment(loan.getAmountPerInstallment());
                loanDTO.setDateFinishInstallment(loan.getDateFinishInstallment());
                loanDTO.setAccountId(loan.getAccount().getId());
                loanDTO.setFundId(loan.getFund().getId());
                loanDTO.setCreatedAt(loan.getFund().getCreatedAt());
                dtoList.add(loanDTO);
            }
            llDTO.setData(dtoList);
            return Optional.ofNullable(llDTO);
        }
        return Optional.empty();
    }

    public Optional<ListLoanDto> findAllDtoByAccountId(Long accountId) {
        Optional<List<Loan>> list = loanRepo.findAllByAccountId((accountId));
        if (list.isPresent()) {
            ListLoanDto llDTO = new ListLoanDto();
            llDTO.setStatus(HttpStatus.OK.value());
            llDTO.setMessage(AppConstants.KEY_SUCESSE);
            List<LoanDto> dtoList = new ArrayList<>();
            for (Loan loan : list.get()) {
                LoanDto loanDTO = new LoanDto();
                loanDTO.setLoanId(loan.getId());
                loanDTO.setCountLoan(loan.getCountLoan());
//                loanDTO.setSumLoan(loan.getSumLoan());
                loanDTO.setCurrentLoanAmount(loan.getCurrentLoanAmount());
                loanDTO.setDateCurrentLoan(loan.getDateCurrentLoan());
                loanDTO.setCountInstallments(loan.getCountInstallments());
                loanDTO.setAmountPerInstallment(loan.getAmountPerInstallment());
                loanDTO.setDateFinishInstallment(loan.getDateFinishInstallment());
                loanDTO.setAccountId(loan.getAccount().getId());
                loanDTO.setFundId(loan.getFund().getId());
                loanDTO.setCreatedAt(loan.getFund().getCreatedAt());
                dtoList.add(loanDTO);
            }
            llDTO.setData(dtoList);
            return Optional.ofNullable(llDTO);
        }
        return Optional.empty();
    }
}
