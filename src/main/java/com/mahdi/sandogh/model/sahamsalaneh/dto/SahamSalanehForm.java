package com.mahdi.sandogh.model.sahamsalaneh.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/18/19
 * Time: 22:11
 * https://github.com/mahdihp
 */


@Setter
@Getter
public class SahamSalanehForm {

    private Long sahamSalanehId;

    @NotNull
    private Integer yesrs; //سال
    @NotNull
    private Long membershipFee; // حق عضویت

    public SahamSalanehForm() {
    }
}
