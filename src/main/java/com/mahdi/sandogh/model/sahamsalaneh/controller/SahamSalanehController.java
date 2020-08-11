package com.mahdi.sandogh.model.sahamsalaneh.controller;

import com.mahdi.sandogh.model.BaseDTO;
import com.mahdi.sandogh.model.sahamsalaneh.dto.ListSahamSalanehDTO;
import com.mahdi.sandogh.model.sahamsalaneh.dto.SahamSalanehDTO;
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
@RequestMapping("/api/v1/sahamsalaneh")
public class SahamSalanehController {

    @Autowired
    private SahamSalanehService sahamSalanehService;

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createSahamSalaneh(@Valid @RequestBody SahamSalanehForm ssForm) {
        sahamSalanehService.create(ssForm);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), AppConstants.KEY_CREATE_SAHAMSALANEH));
    }

    @PostMapping(value = "/{sahamsalanehid}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateSahamSalaneh(@PathVariable(value = "sahamsalanehid") String sahamsalanehId, @Valid @RequestBody SahamSalanehForm ssForm) {
        ssForm.setSahamSalanehId(Long.valueOf(sahamsalanehId));
        sahamSalanehService.update(ssForm);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), AppConstants.KEY_UPDATE_SAHAMSALANEH));
    }

    @PostMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAllSahamSalaneh() {
        Optional<ListSahamSalanehDTO> list = sahamSalanehService.findAllDTO();
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), AppConstants.KEY_NOT_FOUND_SAHAMSALANEH));
    }

    @PostMapping(value = "/one", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findSahamSalaneh(@RequestParam("sahamsalanehid") String sahamsalanehId) {
        Optional<SahamSalanehDTO> sahamsalaneh = sahamSalanehService.findDTO(1, sahamsalanehId);
        if (sahamsalaneh.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(sahamsalaneh.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), AppConstants.KEY_NOT_FOUND_SAHAMSALANEH));
    }
}
