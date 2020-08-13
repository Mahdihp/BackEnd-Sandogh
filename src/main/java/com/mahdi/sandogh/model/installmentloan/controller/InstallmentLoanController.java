package com.mahdi.sandogh.model.installmentloan.controller;

import com.mahdi.sandogh.model.installmentloan.dto.InstallmentLoanDto;
import com.mahdi.sandogh.model.installmentloan.dto.InstallmentLoanForm;
import com.mahdi.sandogh.model.installmentloan.dto.ListInstallmentLoanDto;
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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/installmentloans")
public class InstallmentLoanController {

    @Autowired
    private InstallmentLoanService installmentLoanService;

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createInstallmentLoan(@Valid @RequestBody InstallmentLoanForm ilForm) {
        if (installmentLoanService.create(ilForm))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(HttpStatus.OK.value()).setMessage(AppConstants.KEY_CREATE_INSTALLMENTLOAN).createBaseDto());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(HttpStatus.OK.value()).setMessage(AppConstants.KEY_NOT_FOUND_LOAN).createBaseDto());
    }

    @PostMapping(value = "/{installmentloanid}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateInstallmentLoan(@PathVariable(value = "installmentloanid") String installmentloanid, @Valid @RequestBody InstallmentLoanForm ilForm) {
        ilForm.setAccountId(Long.valueOf(installmentloanid));
        if (installmentLoanService.update(ilForm))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(HttpStatus.OK.value()).setMessage(AppConstants.KEY_UPDATE_INSTALLMENTLOAN).createBaseDto());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(HttpStatus.OK.value()).setMessage(AppConstants.KEY_NOT_FOUND_LOAN).createBaseDto());
    }

    @PostMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAllInstallmentLoan() {
        Optional<ListInstallmentLoanDto> list = installmentLoanService.findAllDTO();
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(HttpStatus.OK.value()).setMessage(AppConstants.KEY_NOT_FOUND_LOAN).createBaseDto());
    }

    @PostMapping(value = "/one", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findInstallmentLoan(@RequestParam("installmentloanid") String installmentloanid) {
        Optional<InstallmentLoanDto> installmentLoanDTO = installmentLoanService.findDTOById(Long.valueOf(installmentloanid));
        if (installmentLoanDTO.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(installmentLoanDTO.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(HttpStatus.OK.value()).setMessage(AppConstants.KEY_NOT_FOUND_LOAN).createBaseDto());
    }

}
