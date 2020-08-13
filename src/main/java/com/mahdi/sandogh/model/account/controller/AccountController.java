package com.mahdi.sandogh.model.account.controller;


import com.mahdi.sandogh.model.BaseDto;
import com.mahdi.sandogh.model.account.dto.AccountForm;
import com.mahdi.sandogh.model.account.dto.AccountReponse;
import com.mahdi.sandogh.model.account.dto.ListAccountDto;
import com.mahdi.sandogh.model.account.service.AccountService;
import com.mahdi.sandogh.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(AppConstants.KEY_API_ACCOUNT)
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> create(@Valid @RequestBody AccountForm accountForm) {
        AccountReponse accountReponse = accountService.create(accountForm);
        return ResponseEntity.status(HttpStatus.OK).body(accountReponse);
    }

    @PostMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@Valid @RequestBody AccountForm accountForm) {
        AccountReponse accountReponse = accountService.update(accountForm);
        return ResponseEntity.status(HttpStatus.OK).body(accountReponse);
    }

    @PostMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll() {
        Optional<ListAccountDto> list = accountService.findAllDto();
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(201, AppConstants.KEY_NOT_FOUND_ACCOUNT_LIST));
    }

    @PostMapping(value = "/one", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAccountByFields(@RequestParam("type") int type, @RequestParam("fields") String fields) {
        Optional<ListAccountDto> list = accountService.findDtoByFields(type, fields);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(201, AppConstants.KEY_NOT_FOUND_ACCOUNT_LIST));
    }

    @PostMapping(value = "/remove", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> remove(@RequestParam("accountnumber") String accountnumber) {
        AccountReponse accountReponse = accountService.remove(accountnumber);
        return ResponseEntity.status(HttpStatus.OK).body(accountReponse);
    }

    @PostMapping(value = "/recovery", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> recovery(@RequestParam("accountnumber") String accountnumber) {
        AccountReponse accountReponse = accountService.recovery(accountnumber);
        return ResponseEntity.status(HttpStatus.OK).body(accountReponse);
    }

    @PostMapping(value = "/deactivate", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deactivate(@RequestParam("accountnumber") String accountnumber) {
        AccountReponse accountReponse = accountService.deactivate(accountnumber);
        return ResponseEntity.status(HttpStatus.OK).body(accountReponse);
    }

    @PostMapping(value = "/activate", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> activate(@RequestParam("accountnumber") String accountnumber) {
        AccountReponse accountReponse = accountService.activate(accountnumber);
        return ResponseEntity.status(HttpStatus.OK).body(accountReponse);
    }

}
