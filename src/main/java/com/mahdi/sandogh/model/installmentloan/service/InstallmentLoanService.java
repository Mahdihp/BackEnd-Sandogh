package com.mahdi.sandogh.model.installmentloan.service;

import com.mahdi.sandogh.model.account.Account;
import com.mahdi.sandogh.model.account.service.AccountService;
import com.mahdi.sandogh.model.installmentloan.InstallmentLoan;
import com.mahdi.sandogh.model.installmentloan.dto.InstallmentLoanDto;
import com.mahdi.sandogh.model.installmentloan.dto.InstallmentLoanForm;
import com.mahdi.sandogh.model.installmentloan.dto.ListInstallmentLoanDto;
import com.mahdi.sandogh.model.installmentloan.dto.ResponseIL;
import com.mahdi.sandogh.model.installmentloan.repository.InstallmentLoanRepo;
import com.mahdi.sandogh.model.loan.Loan;
import com.mahdi.sandogh.model.loan.service.LoanService;
import com.mahdi.sandogh.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class InstallmentLoanService {

    @Autowired
    private InstallmentLoanRepo installmentLoanRepo;

    @Autowired
    private AccountService accountService;

    @Autowired
    private LoanService loanService;

    public ResponseIL create(InstallmentLoanForm form) {
        Optional<Loan> loan = loanService.findById(form.getLoanId());
        Optional<Account> account = accountService.findById(form.getAccountId());
        if (loan.isPresent() && account.isPresent()) {
            InstallmentLoan installmentLoan = new InstallmentLoan();
            installmentLoan.setAmountInstallment(form.getAmountInstallment());
            installmentLoan.setNumberLoan(form.getNumberLoan());
            installmentLoan.setDescription(form.getDescription());
            installmentLoan.setCreateBy(form.getCreateBy());
            installmentLoan.setAccount(account.get());
            installmentLoan.setLoan(loan.get());
            installmentLoanRepo.save(installmentLoan);
            return ResponseIL.Builder.aResponseIL()
                    .withMessage(AppConstants.KEY_CREATE_INSTALLMENT_LOAN)
                    .withStatus(200)
                    .build();
        }
        return ResponseIL.Builder.aResponseIL()
                .withMessage(AppConstants.KEY_NOT_FOUND_LOAN)
                .withStatus(201)
                .build();
    }

    public ResponseIL update(InstallmentLoanForm form) {
        Optional<InstallmentLoan> installmentLoan = installmentLoanRepo.findById((form.getInstallmentLoanId()));
        if (installmentLoan.isPresent()) {
            installmentLoan.get().setAmountInstallment(form.getAmountInstallment());
            installmentLoan.get().setNumberLoan(form.getNumberLoan());
            installmentLoan.get().setDescription(form.getDescription());
            installmentLoan.get().setCreateBy(form.getCreateBy());
//            installmentLoan.get().setAccount(installmentLoan.get().getAccount());
//            installmentLoan.get().setLoan(installmentLoan.get().getLoan());
            installmentLoanRepo.save(installmentLoan.get());
            return ResponseIL.Builder.aResponseIL()
                    .withMessage(AppConstants.KEY_UPDATE_INSTALLMENT_LOAN)
                    .withStatus(200)
                    .build();
        }
        return ResponseIL.Builder.aResponseIL()
                .withMessage(AppConstants.KEY_NOT_FOUND_LOAN)
                .withStatus(201)
                .build();
    }

    public Optional<InstallmentLoan> findById(Long installmentLoanId) {
        Optional<InstallmentLoan> installmentLoan = installmentLoanRepo.findById((installmentLoanId));
        if (installmentLoan.isPresent())
            return Optional.ofNullable(installmentLoan.get());
        else
            return Optional.empty();
    }

    /**
     *  لیست اقساطی که تا به حال این عضو پرداخت کرده
     * @param accountNumber
     * @return
     */
    public Optional<ListInstallmentLoanDto> findDtoByAccountNumber(String accountNumber) {
        Optional<Account> account = accountService.findByAccountNumber(accountNumber);
        if (account.isPresent()) {
            ListInstallmentLoanDto lilDTO = new ListInstallmentLoanDto();
            lilDTO.setStatus(HttpStatus.OK.value());
            lilDTO.setMessage(AppConstants.KEY_SUCESSE);

            List<InstallmentLoanDto> dtoList = new ArrayList<>();
            for (InstallmentLoan installmentLoan : account.get().getInstallmentLoans()) {
                InstallmentLoanDto ilDTO = new InstallmentLoanDto();
                ilDTO.setInstallmentLoanId(installmentLoan.getId());
                ilDTO.setAmountInstallment(installmentLoan.getAmountInstallment());
                ilDTO.setNumberLoan(installmentLoan.getNumberLoan());
                ilDTO.setDescription(installmentLoan.getDescription());
                ilDTO.setCreateBy(installmentLoan.getCreateBy());
                ilDTO.setAccountId(installmentLoan.getAccount().getId());
                ilDTO.setLoanId(installmentLoan.getLoan().getId());
                dtoList.add(ilDTO);
            }
            lilDTO.setData(dtoList);
            return Optional.ofNullable(lilDTO);
        }
        return Optional.empty();
    }

    public Optional<ListInstallmentLoanDto> findDtoById(Long installmentLoanId) {
        Optional<InstallmentLoan> installmentLoan = installmentLoanRepo.findById((installmentLoanId));
        if (installmentLoan.isPresent()) {
            ListInstallmentLoanDto lilDTO = new ListInstallmentLoanDto();
            lilDTO.setStatus(HttpStatus.OK.value());
            lilDTO.setMessage(AppConstants.KEY_SUCESSE);

            InstallmentLoanDto ilDTO = new InstallmentLoanDto();

            ilDTO.setInstallmentLoanId(installmentLoan.get().getId());
            ilDTO.setAmountInstallment(installmentLoan.get().getAmountInstallment());
            ilDTO.setNumberLoan(installmentLoan.get().getNumberLoan());
            ilDTO.setAccountId(installmentLoan.get().getAccount().getId());
            ilDTO.setLoanId(installmentLoan.get().getLoan().getId());
            ilDTO.setDescription(installmentLoan.get().getDescription());
            ilDTO.setCreateBy(installmentLoan.get().getCreateBy());

            lilDTO.setData(Arrays.asList(ilDTO));
            return Optional.ofNullable(lilDTO);
        }
        return Optional.empty();
    }

    /**
     *  لیست اقساط براساس شماره وام
     * @param loanId
     * @return
     */
    public Optional<ListInstallmentLoanDto> findAllDtoILLoan(Integer loanId) {
        Optional<Loan> list = loanService.findById(loanId);
        if (list.isPresent()) {
            ListInstallmentLoanDto lilDTO = new ListInstallmentLoanDto();
            lilDTO.setStatus(HttpStatus.OK.value());
            lilDTO.setMessage(AppConstants.KEY_SUCESSE);

            List<InstallmentLoanDto> dtoList = new ArrayList<>();
            for (InstallmentLoan installmentLoan : list.get().getInstallmentLoans()) {
                InstallmentLoanDto ilDTO = new InstallmentLoanDto();

                ilDTO.setInstallmentLoanId(installmentLoan.getId());
                ilDTO.setAmountInstallment(installmentLoan.getAmountInstallment());
                ilDTO.setNumberLoan(installmentLoan.getNumberLoan());
                ilDTO.setAccountId(installmentLoan.getAccount().getId());
                ilDTO.setLoanId(installmentLoan.getLoan().getId());
                ilDTO.setDescription(installmentLoan.getDescription());
                ilDTO.setCreateBy(installmentLoan.getCreateBy());
                dtoList.add(ilDTO);
            }
            lilDTO.setData(dtoList);
            return Optional.ofNullable(lilDTO);
        }
        return Optional.empty();
    }
}
