package com.mahdi.sandogh.model.loan.service;


import com.mahdi.sandogh.model.account.Account;
import com.mahdi.sandogh.model.account.service.AccountService;
import com.mahdi.sandogh.model.loan.Loan;
import com.mahdi.sandogh.model.loan.dto.ListLoanDTO;
import com.mahdi.sandogh.model.loan.dto.LoanDTO;
import com.mahdi.sandogh.model.loan.dto.LoanForm;
import com.mahdi.sandogh.model.loan.repository.LoanRepo;
import com.mahdi.sandogh.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanService {

    @Autowired
    private LoanRepo loanRepo;

    @Autowired
    private AccountService accountService;

    //  یه عضو چند بار وام می تونه بگیره
    public boolean create(LoanForm loanForm) {
        Optional<Account> account = accountService.findUid(loanForm.getAccountId());
        if (account.isPresent()) {
            Loan loan = new Loan();
            loan.setUid(UUID.randomUUID());
            loan.setCountLoan(loanForm.getCountLoan());
            loan.setSumLoan(loanForm.getSumLoan());
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
        Optional<Loan> loan = loanRepo.findByUid(UUID.fromString(loanForm.getLoanId()));
        if (loan.isPresent()) {
            loan.get().setCountLoan(loanForm.getCountLoan());
            loan.get().setSumLoan(loanForm.getSumLoan());
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

    public Optional<ListLoanDTO> findAllDTO() {
        List<Loan> list = loanRepo.findAll();
        if (list != null) {
            ListLoanDTO llDTO = new ListLoanDTO();
            llDTO.setStatus(HttpStatus.OK.value());
            llDTO.setMessage(Constants.KEY_SUCESSE);
            List<LoanDTO> dtoList = new ArrayList<>();
            for (Loan loan : list) {
                LoanDTO loanDTO = new LoanDTO();
                loanDTO.setLoanId(loan.getUid().toString());
                loanDTO.setCountLoan(loan.getCountLoan());
                loanDTO.setSumLoan(loan.getSumLoan());
                loanDTO.setCurrentLoanAmount(loan.getCurrentLoanAmount());
                loanDTO.setDateCurrentLoan(loan.getDateCurrentLoan());
                loanDTO.setCountInstallments(loan.getCountInstallments());
                loanDTO.setAmountPerInstallment(loan.getAmountPerInstallment());
                loanDTO.setDateFinishInstallment(loan.getDateFinishInstallment());
                loanDTO.setAccountId(loan.getAccount().getUid().toString());
                dtoList.add(loanDTO);
            }
            llDTO.setData(dtoList);
            return Optional.ofNullable(llDTO);
        }
        return Optional.empty();
    }

    public Optional<Loan> findById(String loanId) {
        Optional<Loan> loan = loanRepo.findByUid(UUID.fromString(loanId));
        if (loan.isPresent())
            return Optional.ofNullable(loan.get());
        else
            return Optional.empty();
    }

    public Optional<LoanDTO> findDTOById(String loanId) {
        Optional<Loan> loan = loanRepo.findByUid(UUID.fromString(loanId));
        if (loan.isPresent()) {
            LoanDTO loanDTO = new LoanDTO();
            loanDTO.setStatus(HttpStatus.OK.value());
            loanDTO.setMessage(Constants.KEY_SUCESSE);

            loanDTO.setLoanId(loan.get().getUid().toString());
            loanDTO.setCountLoan(loan.get().getCountLoan());
            loanDTO.setSumLoan(loan.get().getSumLoan());
            loanDTO.setCurrentLoanAmount(loan.get().getCurrentLoanAmount());
            loanDTO.setDateCurrentLoan(loan.get().getDateCurrentLoan());
            loanDTO.setCountInstallments(loan.get().getCountInstallments());
            loanDTO.setAmountPerInstallment(loan.get().getAmountPerInstallment());
            loanDTO.setDateFinishInstallment(loan.get().getDateFinishInstallment());
            loanDTO.setAccountId(loan.get().getAccount().getUid().toString());
            return Optional.ofNullable(loanDTO);
        }
        return Optional.empty();
    }

    public Optional<ListLoanDTO> findAllDTOByAccountId(String accountId) {
        Optional<List<Loan>> list = loanRepo.findAllByAccountUid(UUID.fromString(accountId));
        if (list.isPresent()) {
            ListLoanDTO llDTO = new ListLoanDTO();
            llDTO.setStatus(HttpStatus.OK.value());
            llDTO.setMessage(Constants.KEY_SUCESSE);
            List<LoanDTO> dtoList = new ArrayList<>();
            for (Loan loan : list.get()) {
                LoanDTO loanDTO = new LoanDTO();
                loanDTO.setLoanId(loan.getUid().toString());
                loanDTO.setCountLoan(loan.getCountLoan());
                loanDTO.setSumLoan(loan.getSumLoan());
                loanDTO.setCurrentLoanAmount(loan.getCurrentLoanAmount());
                loanDTO.setDateCurrentLoan(loan.getDateCurrentLoan());
                loanDTO.setCountInstallments(loan.getCountInstallments());
                loanDTO.setAmountPerInstallment(loan.getAmountPerInstallment());
                loanDTO.setDateFinishInstallment(loan.getDateFinishInstallment());
                loanDTO.setAccountId(loan.getAccount().getUid().toString());
                dtoList.add(loanDTO);
            }
            llDTO.setData(dtoList);
            return Optional.ofNullable(llDTO);
        }
        return Optional.empty();
    }
}
