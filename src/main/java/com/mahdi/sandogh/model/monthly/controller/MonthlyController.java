package com.mahdi.sandogh.model.monthly.controller;

import com.mahdi.sandogh.model.BaseDTO;
import com.mahdi.sandogh.model.monthly.dto.ListMonthlyDTO;
import com.mahdi.sandogh.model.monthly.dto.MonthlyDTO;
import com.mahdi.sandogh.model.monthly.dto.MonthlyForm;
import com.mahdi.sandogh.model.monthly.service.MonthlyService;
import com.mahdi.sandogh.utils.Constants;
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
 * Date: 3/18/19
 * Time: 21:57
 * https://github.com/mahdihp
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/monthlys")
public class MonthlyController {

    @Autowired
    private MonthlyService monthlyService;

    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createMonthly(@Valid @RequestBody MonthlyForm monthlyForm) {
        if (monthlyService.create(monthlyForm))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_CREATE_MONTHLY));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_ACCOUNT));
    }

    @PostMapping(value = "/{monthlyid}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> updateAccount(@PathVariable(value = "monthlyid") String monthlyId, @Valid @RequestBody MonthlyForm monthlyForm) {
        monthlyForm.setAccountId(monthlyId);
        if (monthlyService.update(monthlyForm))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_UPDATE_MONTHLY));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_ACCOUNT));

    }

    @PostMapping(value = "/{monthlyid}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllAccount(@PathVariable(value = "monthlyid") String monthlyId) {
        Optional<ListMonthlyDTO> list = monthlyService.findAllDTO(monthlyId);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_ACCOUNT));
    }

    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAccount(@RequestParam("monthlyid") String monthlyid) {
        Optional<MonthlyDTO> monthlyDTO = monthlyService.findDTOById(monthlyid);
        if (monthlyDTO.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(monthlyDTO.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_ACCOUNT));
    }


}
