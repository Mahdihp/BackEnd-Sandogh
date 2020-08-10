package com.mahdi.sandogh.model.installmentloan.service;

import com.mahdi.sandogh.model.account.Account;
import com.mahdi.sandogh.model.account.service.AccountService;
import com.mahdi.sandogh.model.installmentloan.InstallmentLoan;
import com.mahdi.sandogh.model.installmentloan.dto.InstallmentLoanDTO;
import com.mahdi.sandogh.model.installmentloan.dto.InstallmentLoanForm;
import com.mahdi.sandogh.model.installmentloan.dto.ListInstallmentLoanDTO;
import com.mahdi.sandogh.model.installmentloan.repository.InstallmentLoanRepo;
import com.mahdi.sandogh.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InstallmentLoanService {

    @Autowired
    private InstallmentLoanRepo installmentLoanRepo;

    @Autowired
    private AccountService accountService;

    public boolean create(InstallmentLoanForm ilForm) {
        Optional<Account> account = accountService.findById(ilForm.getAccountId());
        if (account.isPresent()) {
            InstallmentLoan installmentLoan = new InstallmentLoan();
            installmentLoan.setUid(UUID.randomUUID());
            installmentLoan.setAmountInstallment(ilForm.getAmountInstallment());
            installmentLoan.setCreationDate(System.currentTimeMillis());
            installmentLoan.setNumberLoan(ilForm.getNumberLoan());
            installmentLoan.setAccount(account.get());
            installmentLoanRepo.save(installmentLoan);
            return true;
        }
        return false;
    }

    public boolean update(InstallmentLoanForm ilForm) {
        Optional<InstallmentLoan> installmentLoan = installmentLoanRepo.findByUid(UUID.fromString(ilForm.getInstallmentLoanId()));
        if (installmentLoan.isPresent()) {
            installmentLoan.get().setAmountInstallment(ilForm.getAmountInstallment());
            installmentLoan.get().setCreationDate(System.currentTimeMillis());
            installmentLoan.get().setNumberLoan(ilForm.getNumberLoan());
            installmentLoan.get().setAccount(installmentLoan.get().getAccount());
            installmentLoanRepo.save(installmentLoan.get());
            return true;
        }
        return false;
    }

    public Optional<InstallmentLoan> findById(String installmentLoanId) {
        Optional<InstallmentLoan> installmentLoan = installmentLoanRepo.findByUid(UUID.fromString(installmentLoanId));
        if (installmentLoan.isPresent())
            return Optional.ofNullable(installmentLoan.get());
        else
            return Optional.empty();
    }

    public Optional<InstallmentLoanDTO> findDTOById(String installmentLoanId) {
        Optional<InstallmentLoan> installmentLoan = installmentLoanRepo.findByUid(UUID.fromString(installmentLoanId));
        if (installmentLoan.isPresent()) {
            InstallmentLoanDTO ilDTO = new InstallmentLoanDTO();
            ilDTO.setStatus(HttpStatus.OK.value());
            ilDTO.setMessage(AppConstants.KEY_SUCESSE);

            ilDTO.setInstallmentLoanId(installmentLoan.get().getUid().toString());
            ilDTO.setAmountInstallment(installmentLoan.get().getAmountInstallment());
            ilDTO.setCreationDate(installmentLoan.get().getCreationDate());
            ilDTO.setNumberLoan(installmentLoan.get().getNumberLoan());
            ilDTO.setAccountId(installmentLoan.get().getAccount().getUid().toString());
            return Optional.ofNullable(ilDTO);
        }
        return Optional.empty();
    }

    public Optional<ListInstallmentLoanDTO> findAllDTOByAccountId(String installmentLoanId) {
        Optional<List<InstallmentLoan>> list = installmentLoanRepo.findByAccountUid(UUID.fromString(installmentLoanId));
        if (list.isPresent()) {
            ListInstallmentLoanDTO lilDTO = new ListInstallmentLoanDTO();
            lilDTO.setStatus(HttpStatus.OK.value());
            lilDTO.setMessage(AppConstants.KEY_SUCESSE);
            List<InstallmentLoanDTO> dtoList = new ArrayList<>();
            for (InstallmentLoan installmentLoan : list.get()) {
                InstallmentLoanDTO ilDTO = new InstallmentLoanDTO();

                ilDTO.setInstallmentLoanId(installmentLoan.getUid().toString());
                ilDTO.setAmountInstallment(installmentLoan.getAmountInstallment());
                ilDTO.setCreationDate(installmentLoan.getCreationDate());
                ilDTO.setNumberLoan(installmentLoan.getNumberLoan());
                ilDTO.setAccountId(installmentLoan.getAccount().getUid().toString());
                dtoList.add(ilDTO);
            }
            lilDTO.setData(dtoList);
            return Optional.ofNullable(lilDTO);
        }
        return Optional.empty();
    }

    public Optional<ListInstallmentLoanDTO> findAllDTO() {
        List<InstallmentLoan> list = installmentLoanRepo.findAll();
        if (list != null) {
            ListInstallmentLoanDTO lilDTO = new ListInstallmentLoanDTO();
            lilDTO.setStatus(HttpStatus.OK.value());
            lilDTO.setMessage(AppConstants.KEY_SUCESSE);
            List<InstallmentLoanDTO> dtoList = new ArrayList<>();
            for (InstallmentLoan installmentLoan : list) {
                InstallmentLoanDTO ilDTO = new InstallmentLoanDTO();

                ilDTO.setInstallmentLoanId(installmentLoan.getUid().toString());
                ilDTO.setAmountInstallment(installmentLoan.getAmountInstallment());
                ilDTO.setCreationDate(installmentLoan.getCreationDate());
                ilDTO.setNumberLoan(installmentLoan.getNumberLoan());
                ilDTO.setAccountId(installmentLoan.getAccount().getUid().toString());
                dtoList.add(ilDTO);
            }
            lilDTO.setData(dtoList);
            return Optional.ofNullable(lilDTO);
        }
        return Optional.empty();
    }
}
