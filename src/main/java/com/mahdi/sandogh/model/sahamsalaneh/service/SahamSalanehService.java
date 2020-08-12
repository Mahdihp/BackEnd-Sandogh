package com.mahdi.sandogh.model.sahamsalaneh.service;

import com.mahdi.sandogh.model.sahamsalaneh.SahamSalaneh;
import com.mahdi.sandogh.model.sahamsalaneh.dto.ListSahamSalanehDto;
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

    public void create(SahamSalanehForm sahamSalanehForm) {
        SahamSalaneh sahamSalaneh = new SahamSalaneh();
//        sahamSalaneh.setUid(UUID.randomUUID());
        sahamSalaneh.setYesrs(sahamSalanehForm.getYesrs());
        sahamSalaneh.setMembershipFee(sahamSalanehForm.getMembershipFee());
//        sahamSalaneh.setCreationDate(System.currentTimeMillis());
        sahamSalanehRepo.save(sahamSalaneh);
    }

    public void update(SahamSalanehForm sahamSalanehForm) {
        Optional<SahamSalaneh> sahamSalaneh = sahamSalanehRepo.findById((sahamSalanehForm.getSahamSalanehId()));
        if (sahamSalaneh.isPresent()) {
            sahamSalaneh.get().setYesrs(sahamSalanehForm.getYesrs());
            sahamSalaneh.get().setMembershipFee(sahamSalanehForm.getMembershipFee());
//            sahamSalaneh.get().setModificationDate(System.currentTimeMillis());
            sahamSalanehRepo.save(sahamSalaneh.get());
        }
    }

    public Optional<SahamSalaneh> findById(Long sahamSalanehId) {
        Optional<SahamSalaneh> sahamSalaneh = sahamSalanehRepo.findById((sahamSalanehId));
        return sahamSalaneh;
    }

    public Optional<SahamSalanehDto> findDTO(int queryType, String item) {
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

//            ssDTO.setSahamSalanehId(sahamSalaneh.get().getUid().toString());
            ssDTO.setMembershipFee(sahamSalaneh.get().getMembershipFee());
//            ssDTO.setCreationDate(sahamSalaneh.get().getCreationDate());
//            ssDTO.setModificationDate(sahamSalaneh.get().getModificationDate());
            return Optional.ofNullable(ssDTO);
        }
        return Optional.empty();
    }

    public Optional<ListSahamSalanehDto> findAllDTO() {
        List<SahamSalaneh> list = sahamSalanehRepo.findAll();
        if (list.isEmpty()) {
            ListSahamSalanehDto lssDTO = new ListSahamSalanehDto();
            lssDTO.setStatus(HttpStatus.OK.value());
            lssDTO.setMessage(AppConstants.KEY_SUCESSE);
            List<SahamSalanehDto> dtoList = new ArrayList<>();
            for (SahamSalaneh sahamSalaneh : list) {
                SahamSalanehDto ssDTO = new SahamSalanehDto();

//                ssDTO.setSahamSalanehId(sahamSalaneh.getUid().toString());
                ssDTO.setMembershipFee(sahamSalaneh.getMembershipFee());
//                ssDTO.setCreationDate(sahamSalaneh.getCreationDate());
//                ssDTO.setModificationDate(sahamSalaneh.getModificationDate());
                dtoList.add(ssDTO);
            }
            lssDTO.setData(dtoList);
            return Optional.ofNullable(lssDTO);
        }
        return Optional.empty();
    }

}
