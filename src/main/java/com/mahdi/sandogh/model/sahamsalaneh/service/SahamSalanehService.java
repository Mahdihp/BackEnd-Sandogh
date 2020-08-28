package com.mahdi.sandogh.model.sahamsalaneh.service;

import com.mahdi.sandogh.model.sahamsalaneh.SahamSalaneh;
import com.mahdi.sandogh.model.sahamsalaneh.dto.ListSahamSalanehDto;
import com.mahdi.sandogh.model.sahamsalaneh.dto.SSResponse;
import com.mahdi.sandogh.model.sahamsalaneh.dto.SahamSalanehDto;
import com.mahdi.sandogh.model.sahamsalaneh.dto.SahamSalanehForm;
import com.mahdi.sandogh.model.sahamsalaneh.repository.SahamSalanehRepo;
import com.mahdi.sandogh.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/18/19
 * Time: 22:05
 * https://github.com/mahdihp
 */


@Service
public class SahamSalanehService {

    @Autowired
    private SahamSalanehRepo sahamSalanehRepo;

    public SSResponse create(SahamSalanehForm sahamSalanehForm) {
        SahamSalaneh sahamSalaneh = new SahamSalaneh();
        sahamSalaneh.setYesrs(sahamSalanehForm.getYesrs());
        sahamSalaneh.setMembershipFee(sahamSalanehForm.getMembershipFee());
        sahamSalanehRepo.save(sahamSalaneh);

        return SSResponse.Builder
                .aSSResponse()
                .withStatus(200)
                .withMessage(AppConstants.KEY_CREATE_SAHAMSALANEH)
                .build();
    }

    public SSResponse update(SahamSalanehForm sahamSalanehForm) {
        Optional<SahamSalaneh> sahamSalaneh = sahamSalanehRepo.findById((sahamSalanehForm.getSahamSalanehId()));
        if (sahamSalaneh.isPresent()) {
            sahamSalaneh.get().setYesrs(sahamSalanehForm.getYesrs());
            sahamSalaneh.get().setMembershipFee(sahamSalanehForm.getMembershipFee());
            sahamSalanehRepo.save(sahamSalaneh.get());
            return SSResponse.Builder
                    .aSSResponse()
                    .withStatus(200)
                    .withMessage(AppConstants.KEY_UPDATE_SAHAMSALANEH)
                    .build();
        }
        return SSResponse.Builder
                .aSSResponse()
                .withStatus(201)
                .withMessage(AppConstants.KEY_NOT_FOUND_SAHAMSALANEH)
                .build();
    }

    public Optional<SahamSalaneh> findById(Long sahamSalanehId) {
        Optional<SahamSalaneh> sahamSalaneh = sahamSalanehRepo.findById((sahamSalanehId));
        return sahamSalaneh;
    }

    public Optional<SahamSalanehDto> findDto(int queryType, String item) {
        Optional<SahamSalaneh> sahamSalaneh = Optional.empty();
        switch (queryType) {
            case 1:
                sahamSalaneh = sahamSalanehRepo.findById(Long.valueOf((item)));
                break;
            case 2:
                sahamSalaneh = sahamSalanehRepo.findByYesrs(Integer.parseInt(item));
                break;
            case 3:
                sahamSalaneh = sahamSalanehRepo.findByMembershipFee(Long.valueOf(item));
                break;
        }

        if (sahamSalaneh.isPresent()) {
            SahamSalanehDto ssDTO = new SahamSalanehDto();
            ssDTO.setStatus(HttpStatus.OK.value());
            ssDTO.setMessage(AppConstants.KEY_SUCESSE);

            ssDTO.setSahamSalanehId(sahamSalaneh.get().getId());
            ssDTO.setMembershipFee(sahamSalaneh.get().getMembershipFee());
            ssDTO.setCreationDate(sahamSalaneh.get().getCreatedAt());
            ssDTO.setModificationDate(sahamSalaneh.get().getUpdatedAt());
            return Optional.ofNullable(ssDTO);
        }
        return Optional.empty();
    }

    public Optional<ListSahamSalanehDto> findAllDto() {
        List<SahamSalaneh> list = sahamSalanehRepo.findAll();
        if (list.isEmpty()) {
            ListSahamSalanehDto lssDTO = new ListSahamSalanehDto();
            lssDTO.setStatus(HttpStatus.OK.value());
            lssDTO.setMessage(AppConstants.KEY_SUCESSE);
            List<SahamSalanehDto> dtoList = new ArrayList<>();
            for (SahamSalaneh sahamSalaneh : list) {
                SahamSalanehDto ssDTO = new SahamSalanehDto();

                ssDTO.setSahamSalanehId(sahamSalaneh.getId());
                ssDTO.setMembershipFee(sahamSalaneh.getMembershipFee());
                ssDTO.setCreationDate(sahamSalaneh.getCreatedAt());
                ssDTO.setModificationDate(sahamSalaneh.getUpdatedAt());
                dtoList.add(ssDTO);
            }
            lssDTO.setData(dtoList);
            return Optional.ofNullable(lssDTO);
        }
        return Optional.empty();
    }

}
