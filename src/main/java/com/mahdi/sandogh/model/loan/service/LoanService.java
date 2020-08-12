package com.mahdi.sandogh.model.loan.service;


import com.mahdi.sandogh.model.account.Account;
import com.mahdi.sandogh.model.account.service.AccountService;
import com.mahdi.sandogh.model.loan.Loan;
import com.mahdi.sandogh.model.loan.dto.ListLoanDto;
import com.mahdi.sandogh.model.loan.dto.LoanDto;
import com.mahdi.sandogh.model.loan.dto.LoanForm;
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

    //  یه عضو چند بار وام می تونه بگیره
    public boolean create(LoanForm loanForm) {
        Optional<Account> account = accountService.findById(loanForm.getAccountId());
        if (account.isPresent()) {
            Loan loan = new Loan();
//            loan.setUid(UUID.randomUUID());
            loan.setCountLoan(loanForm.getCountLoan());
//            loan.setSumLoan(loanForm.getSumLoan());
            loan.setCurrentLoanAmount(loanForm.getCurrentLoanAmount());
            loan.setDateCurrentLoan(loanForm.getDateCurrentLoan());
            loan.setCountInstallments(loanForm.getCountInstallments());
            loan.setAmountPerInstallment(loanForm.getAmountPerInstallment());
            loan.setDateFinishInstallment(loanForm.getDateFinishInstallment());
            loan.setAccount(account.get());
            loanRepo.save(loan);
            return true;
        }
        return false;
    }

    public boolean update(LoanForm loanForm) {
        Optional<Loan> loan = loanRepo.findById((loanForm.getLoanId()));
        if (loan.isPresent()) {
            loan.get().setCountLoan(loanForm.getCountLoan());
//            loan.get().setSumLoan(loanForm.getSumLoan());
            loan.get().setCurrentLoanAmount(loanForm.getCurrentLoanAmount());
            loan.get().setDateCurrentLoan(loanForm.getDateCurrentLoan());
            loan.get().setCountInstallments(loanForm.getCountInstallments());
            loan.get().setAmountPerInstallment(loanForm.getAmountPerInstallment());
            loan.get().setDateFinishInstallment(loanForm.getDateFinishInstallment());
            loan.get().setAccount(loan.get().getAccount());
            loanRepo.save(loan.get());
            return true;
        }
        return false;
    }

    public Optional<ListLoanDto> findAllDTO() {
        List<Loan> list = loanRepo.findAll();
        if (list != null) {
            ListLoanDto llDTO = new ListLoanDto();
            llDTO.setStatus(HttpStatus.OK.value());
            llDTO.setMessage(AppConstants.KEY_SUCESSE);
            List<LoanDto> dtoList = new ArrayList<>();
            for (Loan loan : list) {
                LoanDto loanDTO = new LoanDto();
//                loanDTO.setLoanId(loan.getUid().toString());
                loanDTO.setCountLoan(loan.getCountLoan());
//                loanDTO.setSumLoan(loan.getSumLoan());
                loanDTO.setCurrentLoanAmount(loan.getCurrentLoanAmount());
                loanDTO.setDateCurrentLoan(loan.getDateCurrentLoan());
                loanDTO.setCountInstallments(loan.getCountInstallments());
                loanDTO.setAmountPerInstallment(loan.getAmountPerInstallment());
                loanDTO.setDateFinishInstallment(loan.getDateFinishInstallment());
//                loanDTO.setAccountId(loan.getAccount().getUid().toString());
                dtoList.add(loanDTO);
            }
            llDTO.setData(dtoList);
            return Optional.ofNullable(llDTO);
        }
        return Optional.empty();
    }

    public Optional<Loan> findById(Long loanId) {
        Optional<Loan> loan = loanRepo.findById((loanId));
        if (loan.isPresent())
            return Optional.ofNullable(loan.get());
        else
            return Optional.empty();
    }

    public Optional<LoanDto> findDTOById(Long loanId) {
        Optional<Loan> loan = loanRepo.findById((loanId));
        if (loan.isPresent()) {
            LoanDto loanDTO = new LoanDto();
            loanDTO.setStatus(HttpStatus.OK.value());
            loanDTO.setMessage(AppConstants.KEY_SUCESSE);

//            loanDTO.setLoanId(loan.get().getUid().toString());
            loanDTO.setCountLoan(loan.get().getCountLoan());
//            loanDTO.setSumLoan(loan.get().getSumLoan());
            loanDTO.setCurrentLoanAmount(loan.get().getCurrentLoanAmount());
            loanDTO.setDateCurrentLoan(loan.get().getDateCurrentLoan());
            loanDTO.setCountInstallments(loan.get().getCountInstallments());
            loanDTO.setAmountPerInstallment(loan.get().getAmountPerInstallment());
            loanDTO.setDateFinishInstallment(loan.get().getDateFinishInstallment());
//            loanDTO.setAccountId(loan.get().getAccount().getUid().toString());
            return Optional.ofNullable(loanDTO);
        }
        return Optional.empty();
    }

    public Optional<ListLoanDto> findAllDTOByAccountId(Long accountId) {
        Optional<List<Loan>> list = loanRepo.findAllByAccountId((accountId));
        if (list.isPresent()) {
            ListLoanDto llDTO = new ListLoanDto();
            llDTO.setStatus(HttpStatus.OK.value());
            llDTO.setMessage(AppConstants.KEY_SUCESSE);
            List<LoanDto> dtoList = new ArrayList<>();
            for (Loan loan : list.get()) {
                LoanDto loanDTO = new LoanDto();
//                loanDTO.setLoanId(loan.getUid().toString());
                loanDTO.setCountLoan(loan.getCountLoan());
//                loanDTO.setSumLoan(loan.getSumLoan());
                loanDTO.setCurrentLoanAmount(loan.getCurrentLoanAmount());
                loanDTO.setDateCurrentLoan(loan.getDateCurrentLoan());
                loanDTO.setCountInstallments(loan.getCountInstallments());
                loanDTO.setAmountPerInstallment(loan.getAmountPerInstallment());
                loanDTO.setDateFinishInstallment(loan.getDateFinishInstallment());
//                loanDTO.setAccountId(loan.getAccount().getUid().toString());
                dtoList.add(loanDTO);
            }
            llDTO.setData(dtoList);
            return Optional.ofNullable(llDTO);
        }
        return Optional.empty();
    }
}
