package com.mahdi.sandogh.model.found.controller;

import com.mahdi.sandogh.model.BaseDto;
import com.mahdi.sandogh.model.found.dto.FundForm;
import com.mahdi.sandogh.model.found.dto.ListFundDto;
import com.mahdi.sandogh.model.found.service.FundService;
import com.mahdi.sandogh.model.loan.dto.ListLoanDto;
import com.mahdi.sandogh.model.loan.dto.LoanDto;
import com.mahdi.sandogh.model.loan.dto.LoanForm;
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
 * DateTime: ۱۲/۰۸/۲۰۲۰ - 18:30
 * http://github.com/mahdihp
 * http://gitlab.com/mahdihp
 */

@RestController
@RequestMapping(AppConstants.KEY_API_FUND)
public class FundController {

    @Autowired
    private FundService service;

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> create(@Valid @RequestBody FundForm fundForm) {
        if (service.create(fundForm))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(HttpStatus.OK.value(), AppConstants.KEY_CREATE_FUND));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(201, AppConstants.KEY_NOT_FOUND_ACCOUNT));
    }

    @PostMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@Valid @RequestBody FundForm fundForm) {
        if (service.update(fundForm))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(HttpStatus.OK.value(), AppConstants.KEY_UPDATE_FUND));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(201, AppConstants.KEY_NOT_FOUND_ACCOUNT));
    }

    @GetMapping(value = "/one", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getOne(@RequestParam("fundid") Integer fundid) {
        Optional<ListFundDto> fund = service.findFundById(fundid);
        if (fund.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(fund.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(HttpStatus.OK.value(), AppConstants.KEY_NOT_FOUND_FUND));
    }

    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAll() {
        Optional<ListFundDto> list = service.findAllFund();
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(201, AppConstants.KEY_NOT_FOUND_FUND));
    }

    @PostMapping(value = "/remove", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> remove(@RequestParam("fundid") Integer fundid) {
        if (service.removeFund(fundid))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(HttpStatus.OK.value(), AppConstants.KEY_REMOVE_FUND));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(201, AppConstants.KEY_NOT_FOUND_FUND));
    }
}
