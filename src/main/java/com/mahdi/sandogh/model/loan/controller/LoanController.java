package com.mahdi.sandogh.model.loan.controller;

import com.mahdi.sandogh.model.BaseDTO;
import com.mahdi.sandogh.model.loan.dto.ListLoanDTO;
import com.mahdi.sandogh.model.loan.dto.LoanDTO;
import com.mahdi.sandogh.model.loan.dto.LoanForm;
import com.mahdi.sandogh.model.loan.service.LoanService;
import com.mahdi.sandogh.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createLoan(@Valid @RequestBody LoanForm loanForm) {
        if (loanService.create(loanForm))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_CREATE_LOAN));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_ACCOUNT));
    }

    @PostMapping(value = "/{loanid}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> updateLoan(@PathVariable(value = "loanid") String loanId, @Valid @RequestBody LoanForm loanForm) {
        loanForm.setAccountId(loanId);
        if (loanService.update(loanForm))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_UPDATE_LOAN));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_ACCOUNT));
    }

    @PostMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllLoan() {
        Optional<ListLoanDTO> list = loanService.findAllDTO();
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_LOAN));
    }

    @PostMapping(value = "/one", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findLoan(@RequestParam("type") int type, @RequestParam("loanid") String loanId) {
        Optional<LoanDTO> loan = loanService.findDTOById(loanId);
        if (loan.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(loan.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_LOAN));
    }

}
