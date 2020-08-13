package com.mahdi.sandogh.model.monthly.controller;

import com.mahdi.sandogh.model.monthly.dto.ListMonthlyDto;
import com.mahdi.sandogh.model.monthly.dto.MonthlyDto;
import com.mahdi.sandogh.model.monthly.dto.MonthlyForm;
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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/monthlys")
public class MonthlyController {

    @Autowired
    private MonthlyService monthlyService;

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createMonthly(@Valid @RequestBody MonthlyForm monthlyForm) {
        if (monthlyService.create(monthlyForm))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(HttpStatus.OK.value()).setMessage(AppConstants.KEY_CREATE_MONTHLY).createBaseDto());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(HttpStatus.OK.value()).setMessage(AppConstants.KEY_NOT_FOUND_ACCOUNT).createBaseDto());
    }

    @PostMapping(value = "/{monthlyid}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateAccount(@PathVariable(value = "monthlyid") String monthlyId, @Valid @RequestBody MonthlyForm monthlyForm) {
        monthlyForm.setAccountId(Long.valueOf(monthlyId));
        if (monthlyService.update(monthlyForm))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(HttpStatus.OK.value()).setMessage(AppConstants.KEY_UPDATE_MONTHLY).createBaseDto());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(HttpStatus.OK.value()).setMessage(AppConstants.KEY_NOT_FOUND_ACCOUNT).createBaseDto());

    }

    @PostMapping(value = "/all/{monthlyid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAllAccount(@PathVariable(value = "monthlyid") String monthlyId) {
        Optional<ListMonthlyDto> list = monthlyService.findAllDTO(Long.valueOf(monthlyId));
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(HttpStatus.OK.value()).setMessage(AppConstants.KEY_NOT_FOUND_ACCOUNT).createBaseDto());
    }

    @PostMapping(value = "/one", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAccount(@RequestParam("monthlyid") String monthlyid) {
        Optional<MonthlyDto> monthlyDTO = monthlyService.findDTOById(Long.valueOf(monthlyid));
        if (monthlyDTO.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(monthlyDTO.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(HttpStatus.OK.value()).setMessage(AppConstants.KEY_NOT_FOUND_ACCOUNT).createBaseDto());
    }


}
