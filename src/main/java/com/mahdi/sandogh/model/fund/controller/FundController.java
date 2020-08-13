package com.mahdi.sandogh.model.fund.controller;

import com.mahdi.sandogh.model.fund.dto.FundForm;
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
        if (service.create(fundForm))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(HttpStatus.OK.value()).setMessage(AppConstants.KEY_CREATE_FUND).createBaseDto());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(201).setMessage(AppConstants.KEY_NOT_FOUND_ACCOUNT).createBaseDto());
    }

    @PostMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@Valid @RequestBody FundForm fundForm) {
        if (service.update(fundForm))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(HttpStatus.OK.value()).setMessage(AppConstants.KEY_UPDATE_FUND).createBaseDto());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(201).setMessage(AppConstants.KEY_NOT_FOUND_ACCOUNT).createBaseDto());
    }

    @GetMapping(value = "/one", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getOne(@RequestParam("fundid") Integer fundid) {
        Optional<ListFundDto> fund = service.findFundDtoById(fundid);
        if (fund.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(fund.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(HttpStatus.OK.value()).setMessage(AppConstants.KEY_NOT_FOUND_FUND).createBaseDto());
    }

    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAll() {
        Optional<ListFundDto> list = service.findAllFund();
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(201).setMessage(AppConstants.KEY_NOT_FOUND_FUND).createBaseDto());
    }

    @PostMapping(value = "/remove", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> remove(@RequestParam("fundid") Integer fundid) {
        if (service.removeFund(fundid))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(HttpStatus.OK.value()).setMessage(AppConstants.KEY_REMOVE_FUND).createBaseDto());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(201).setMessage(AppConstants.KEY_NOT_FOUND_FUND).createBaseDto());
    }

    @PostMapping(value = "/addfund", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addToFund(@RequestParam("accountnumber") String accountnumber,
                                       @RequestParam("fundId") Integer fundId) {
        service.addAccountToFund(accountnumber,fundId);
    }
}
