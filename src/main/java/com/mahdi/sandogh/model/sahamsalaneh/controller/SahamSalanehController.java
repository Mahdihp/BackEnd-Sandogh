package com.mahdi.sandogh.model.sahamsalaneh.controller;

import com.mahdi.sandogh.model.BaseDto;
import com.mahdi.sandogh.model.sahamsalaneh.dto.ListSahamSalanehDto;
import com.mahdi.sandogh.model.sahamsalaneh.dto.SSResponse;
import com.mahdi.sandogh.model.sahamsalaneh.dto.SahamSalanehDto;
import com.mahdi.sandogh.model.sahamsalaneh.dto.SahamSalanehForm;
import com.mahdi.sandogh.model.sahamsalaneh.service.SahamSalanehService;
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
 * Time: 10:51
 * https://github.com/mahdihp
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(AppConstants.KEY_API_SAHAM_SALANEH)
public class SahamSalanehController {

    @Autowired
    private SahamSalanehService sahamSalanehService;

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> create(@Valid @RequestBody SahamSalanehForm ssForm) {
        SSResponse ssResponse = sahamSalanehService.create(ssForm);
        return ResponseEntity.status(HttpStatus.OK).body(ssResponse);
    }

    @PostMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@Valid @RequestBody SahamSalanehForm ssForm) {
        SSResponse ssResponse = sahamSalanehService.update(ssForm);
        return ResponseEntity.status(HttpStatus.OK).body(ssResponse);
    }

    @PostMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAllSahamSalaneh() {
        Optional<ListSahamSalanehDto> list = sahamSalanehService.findAllDto();
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(201,AppConstants.KEY_NOT_FOUND_SAHAMSALANEH));
    }

    @PostMapping(value = "/one", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findSahamSalaneh(@RequestParam("sahamsalanehid") String sahamsalanehId) {
        Optional<SahamSalanehDto> sahamsalaneh = sahamSalanehService.findDto(1, sahamsalanehId);
        if (sahamsalaneh.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(sahamsalaneh.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(201,AppConstants.KEY_NOT_FOUND_SAHAMSALANEH));
    }
}
