package com.mahdi.sandogh.model.bank.controller;

import com.mahdi.sandogh.model.BaseDto;
import com.mahdi.sandogh.model.bank.dto.BankForm;
import com.mahdi.sandogh.model.bank.dto.BankResponse;
import com.mahdi.sandogh.model.bank.dto.ListBankDto;
import com.mahdi.sandogh.model.bank.service.BankService;
import com.mahdi.sandogh.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by: mahdi
 * DateTime: ۱۹/۰۸/۲۰۲۰ - 20:06:10
 * Email: mahdip.devsc@gmail.com
 * http://github.com/mahdihp & http://gitlab.com/mahdihp
 */

@RestController
@RequestMapping(AppConstants.KEY_API_BANK)
public class BankController {
    @Autowired
    private BankService service;

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> create(@Valid @RequestBody BankForm fundForm) {
        BankResponse fundResponse = service.create(fundForm);
        return ResponseEntity.status(HttpStatus.OK).body(fundResponse);
    }

    @PostMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@Valid @RequestBody BankForm fundForm) {
        BankResponse fundResponse = service.update(fundForm);
        return ResponseEntity.status(HttpStatus.OK).body(fundResponse);
    }

    @GetMapping(value = "/one", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getOne(@RequestParam("bankid") Integer fundid) {
        Optional<ListBankDto> bank = service.findById(fundid);
        if (bank.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(bank.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(201, AppConstants.KEY_NOT_FOUND_BANK));
    }

    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAll() {
        Optional<ListBankDto> list = service.findAllBank();
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(201, AppConstants.KEY_NOT_FOUND_BANK));
    }

    @PostMapping(value = "/remove", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> remove(@RequestParam("bankid") Integer fundid) {
        BankResponse fundResponse = service.remove(fundid);
        return ResponseEntity.status(HttpStatus.OK).body(fundResponse);
    }
}
