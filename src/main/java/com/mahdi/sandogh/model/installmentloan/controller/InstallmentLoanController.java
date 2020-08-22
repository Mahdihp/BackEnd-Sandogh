package com.mahdi.sandogh.model.installmentloan.controller;

import com.mahdi.sandogh.model.BaseDto;
import com.mahdi.sandogh.model.installmentloan.dto.InstallmentLoanDto;
import com.mahdi.sandogh.model.installmentloan.dto.InstallmentLoanForm;
import com.mahdi.sandogh.model.installmentloan.dto.ListInstallmentLoanDto;
import com.mahdi.sandogh.model.installmentloan.dto.ResponseIL;
import com.mahdi.sandogh.model.installmentloan.service.InstallmentLoanService;
import com.mahdi.sandogh.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/20/19
 * Time: 13:35
 * https://github.com/mahdihp
 */

@RestController
@RequestMapping(AppConstants.KEY_API_INSTALLMENT_LOAN)
public class InstallmentLoanController {

    @Autowired
    private InstallmentLoanService installmentLoanService;

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> create(@Valid @RequestBody InstallmentLoanForm ilForm) {
        ResponseIL responseIL = installmentLoanService.create(ilForm);
        return ResponseEntity.status(HttpStatus.OK).body(responseIL);
    }

    @PostMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@Valid @RequestBody InstallmentLoanForm ilForm) {
        ResponseIL responseIL = installmentLoanService.update(ilForm);
        return ResponseEntity.status(HttpStatus.OK).body(responseIL);

    }

    @PostMapping(value = "/listaccount", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAllILAccount(@RequestParam("accountnumber") String accountNumber) {
        Optional<ListInstallmentLoanDto> list = installmentLoanService.findDtoByAccountNumber(accountNumber);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(201, AppConstants.KEY_NOT_FOUND_LOAN));
    }

    @PostMapping(value = "/listaccount", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAllILLoan(@RequestParam("loanid") Integer loanId) {
        Optional<ListInstallmentLoanDto> list = installmentLoanService.findAllDtoILLoan(loanId);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(201, AppConstants.KEY_NOT_FOUND_LOAN));
    }

    @PostMapping(value = "/one", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findInstallmentLoan(@RequestParam("installmentloanid") String installmentloanid) {
        Optional<ListInstallmentLoanDto> loanDto = installmentLoanService.findDtoById(Long.valueOf(installmentloanid));
        if (loanDto.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(loanDto.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(201, AppConstants.KEY_NOT_FOUND_LOAN));
    }

}
