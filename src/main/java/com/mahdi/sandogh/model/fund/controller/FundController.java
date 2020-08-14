package com.mahdi.sandogh.model.fund.controller;

import com.mahdi.sandogh.model.BaseDto;
import com.mahdi.sandogh.model.fund.dto.FundForm;
import com.mahdi.sandogh.model.fund.dto.FundResponse;
import com.mahdi.sandogh.model.fund.dto.ListFundDto;
import com.mahdi.sandogh.model.fund.service.FundService;
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
        FundResponse fundResponse = service.create(fundForm);
        return ResponseEntity.status(HttpStatus.OK).body(fundResponse);
    }

    @PostMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@Valid @RequestBody FundForm fundForm) {
        FundResponse fundResponse = service.update(fundForm);
        return ResponseEntity.status(HttpStatus.OK).body(fundResponse);
    }

    @GetMapping(value = "/one", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getOne(@RequestParam("fundid") Integer fundid) {
        Optional<ListFundDto> fund = service.findFundDtoById(fundid);
        if (fund.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(fund.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(201, AppConstants.KEY_NOT_FOUND_FUND));
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
        FundResponse fundResponse = service.remove(fundid);
        return ResponseEntity.status(HttpStatus.OK).body(fundResponse);
    }

    @PostMapping(value = "/recovery", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> recovery(@RequestParam("fundid") Integer fundid) {
        FundResponse fundResponse = service.recovery(fundid);
        return ResponseEntity.status(HttpStatus.OK).body(fundResponse);
    }

    @PostMapping(value = "/addtofund", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addToFund(@RequestParam("accountnumber") String accountnumber,
                                       @RequestParam("fundId") Integer fundId) {
        FundResponse fundResponse = service.addAccountToFund(accountnumber, fundId);
        return ResponseEntity.status(HttpStatus.OK).body(fundResponse);
    }

    @PostMapping(value = "/removeasfund", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> removeAsFund(@RequestParam("accountnumber") String accountnumber,
                                       @RequestParam("fundId") Integer fundId) {
        FundResponse fundResponse = service.removeAccountAsFund(accountnumber, fundId);
        return ResponseEntity.status(HttpStatus.OK).body(fundResponse);
    }
}
