package com.mahdi.sandogh.model.account.controller;


import com.mahdi.sandogh.model.BaseDTO;
import com.mahdi.sandogh.model.account.dto.AccountDTO;
import com.mahdi.sandogh.model.account.dto.AccountForm;
import com.mahdi.sandogh.model.account.dto.ListAccountDTO;
import com.mahdi.sandogh.model.account.service.AccountService;
import com.mahdi.sandogh.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    //    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountForm accountForm) {
        UUID uid = accountService.create(accountForm);
        if (uid != null)
            return ResponseEntity.status(HttpStatus.OK).body(new AccountDTO(HttpStatus.OK.value(), AppConstants.KEY_CREATE_ACCOUNT, uid.toString()));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), AppConstants.KEY_NOT_FOUND_ACCOUNT));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/{accountid}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateAccount(@PathVariable(value = "accountid") String accountid, @Valid @RequestBody AccountForm accountForm) {
        accountForm.setAccountId(Long.valueOf(accountid));
        if (accountService.update(accountForm))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), AppConstants.KEY_UPDATE_ACCOUNT));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), AppConstants.KEY_NOT_FOUND_ACCOUNT));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAllAccount() {
        Optional<ListAccountDTO> list = accountService.findAllDTO();
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), AppConstants.KEY_NOT_FOUND_ACCOUNT));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/one", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAccount(@RequestParam("type") int type, @RequestParam("accountid") String accountid) {
        Optional<AccountDTO> accountDTO = accountService.findDTOByUid(type, accountid);
        if (accountDTO.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(accountDTO.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), AppConstants.KEY_NOT_FOUND_ACCOUNT));
    }

}
