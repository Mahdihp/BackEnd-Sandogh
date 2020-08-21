package com.mahdi.sandogh.model.loan.controller;

import com.mahdi.sandogh.model.BaseDto;
import com.mahdi.sandogh.model.loan.Loan;
import com.mahdi.sandogh.model.loan.dto.ListLoanDto;
import com.mahdi.sandogh.model.loan.dto.LoanDto;
import com.mahdi.sandogh.model.loan.dto.LoanForm;
import com.mahdi.sandogh.model.loan.dto.LoanResponse;
import com.mahdi.sandogh.model.loan.service.LoanService;
import com.mahdi.sandogh.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping(AppConstants.KEY_API_LOAN)
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> create(@Valid @RequestBody LoanForm loanForm) {
        LoanResponse loanResponse = loanService.create(loanForm);
        return ResponseEntity.status(HttpStatus.OK).body(loanResponse);
    }

    @PostMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@Valid @RequestBody LoanForm loanForm) {
        LoanResponse loanResponse = loanService.update(loanForm);
        return ResponseEntity.status(HttpStatus.OK).body(loanResponse);

    }

    @PostMapping(value = "/list", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAllLoanByFundId(@RequestParam("loanid") String loanId) {
        Optional<ListLoanDto> list = loanService.findAllByFundId(Integer.valueOf(loanId));
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(201, AppConstants.KEY_NOT_FOUND_LOAN));
    }

    @PostMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAllLoan() {
        Optional<ListLoanDto> list = loanService.findAllDto();
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(201, AppConstants.KEY_NOT_FOUND_LOAN));
    }

    @PostMapping(value = "/one", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findLoan(@RequestParam("loanid") String loanId) {
        Optional<Loan> loan = loanService.findById(Long.valueOf(loanId));
        if (loan.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(loan.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(201, AppConstants.KEY_NOT_FOUND_LOAN));
    }

}
