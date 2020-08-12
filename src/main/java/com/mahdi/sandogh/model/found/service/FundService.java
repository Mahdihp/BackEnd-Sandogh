package com.mahdi.sandogh.model.found.service;

import com.mahdi.sandogh.model.found.Fund;
import com.mahdi.sandogh.model.found.dto.FundDto;
import com.mahdi.sandogh.model.found.dto.FundForm;
import com.mahdi.sandogh.model.found.dto.ListFundDto;
import com.mahdi.sandogh.model.found.repository.FundRepo;
import com.mahdi.sandogh.model.loan.dto.ListLoanDto;
import com.mahdi.sandogh.model.loan.dto.LoanDto;
import com.mahdi.sandogh.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by mahdi
 * DateTime: ۱۲/۰۸/۲۰۲۰ - 17:35
 * http://github.com/mahdihp
 * http://gitlab.com/mahdihp
 */

@Service
public class FundService {

    @Autowired
    private FundRepo fundRepo;

    public boolean create(FundForm form) {
        List<Fund> list = fundRepo.findAllByDisplayNameAndDeleted(form.getDisplayName(),false);
        if (list != null && list.size() == 0) {
            Fund fund = new Fund();
            fund.setDisplayName(form.getDisplayName());
            fund.setCreateBy(form.getCreateBy());
            fund.setDescription(form.getDescription());
            fundRepo.save(fund);
            return true;
        }
        return false;
    }

    public boolean update(FundForm form) {
        Optional<Fund> fund = fundRepo.findById(form.getId());
        if (fund.isPresent()) {
            fund.get().setDisplayName(form.getDisplayName());
            fund.get().setCreateBy(form.getCreateBy());
            fund.get().setDescription(form.getDescription());
            fundRepo.save(fund.get());
            return true;
        }
        return false;
    }

    public Optional<ListFundDto> findFundById(Integer id) {
        Optional<Fund> fund = fundRepo.findById(id);
        if (fund.isPresent()) {
            ListFundDto fundDto = new ListFundDto();
            fundDto.setStatus(HttpStatus.OK.value());
            fundDto.setMessage(AppConstants.KEY_SUCESSE);
            FundDto fundDto1 = new FundDto();
            fundDto1.setDisplayName(fund.get().getDisplayName());
            fundDto1.setCreateBy(fund.get().getCreateBy());
            fundDto1.setDescription(fund.get().getDescription());

            fundDto.setData(Arrays.asList(fundDto1));
            return Optional.ofNullable(fundDto);
        }
        return Optional.empty();
    }

    public Optional<ListFundDto> findAllFund() {
        List<Fund> list = fundRepo.findAllByDeleted(false);
        if (list != null && list.size() == 0) {
            ListFundDto llDTO = new ListFundDto();
            llDTO.setStatus(HttpStatus.OK.value());
            llDTO.setMessage(AppConstants.KEY_SUCESSE);
            List<FundDto> dtoList = new ArrayList<>();
            for (Fund fund : list) {
                FundDto fundDto = new FundDto();
                fundDto.setDisplayName(fund.getDisplayName());
                fundDto.setCreateBy(fund.getCreateBy());
                fundDto.setDescription(fund.getDescription());
                dtoList.add(fundDto);
            }
            llDTO.setData(dtoList);
            return Optional.ofNullable(llDTO);
        }
        return Optional.empty();
    }

    public boolean removeFund(Integer id) {
        Optional<Fund> fund = fundRepo.findById(id);
        if (fund.isPresent()) {
            fund.get().setDeleted(true);
            fundRepo.save(fund.get());
            return true;
        }
        return false;
    }
}
