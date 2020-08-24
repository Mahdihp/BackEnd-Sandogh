package com.mahdi.sandogh.model.monthly.controller;

import com.mahdi.sandogh.model.BaseDto;
import com.mahdi.sandogh.model.monthly.dto.ListMonthlyDto;
import com.mahdi.sandogh.model.monthly.dto.MonthlyDto;
import com.mahdi.sandogh.model.monthly.dto.MonthlyForm;
import com.mahdi.sandogh.model.monthly.dto.MonthlyResponse;
import com.mahdi.sandogh.model.monthly.service.MonthlyService;
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
 * Date: 3/18/19
 * Time: 21:57
 * https://github.com/mahdihp
 */

@RestController
@RequestMapping(AppConstants.KEY_API_MONTHLYS)
public class MonthlyController {

    @Autowired
    private MonthlyService service;

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> create(@Valid @RequestBody MonthlyForm monthlyForm) {
        MonthlyResponse monthlyResponse = service.create(monthlyForm);
        return ResponseEntity.status(HttpStatus.OK).body(monthlyResponse);
    }

    @PostMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@Valid @RequestBody MonthlyForm monthlyForm) {
        MonthlyResponse monthlyResponse = service.update(monthlyForm);
        return ResponseEntity.status(HttpStatus.OK).body(monthlyResponse);
    }

    @PostMapping(value = "/list", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAllMonthlyAccount(@RequestParam("accountnumber") String accountNumber) {
        Optional<ListMonthlyDto> list = service.findAllDto(accountNumber);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(201, AppConstants.KEY_NOT_FOUND_ACCOUNT));
    }

}
