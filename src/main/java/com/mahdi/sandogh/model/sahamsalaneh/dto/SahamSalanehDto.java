package com.mahdi.sandogh.model.sahamsalaneh.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/18/19
 * Time: 22:21
 * https://github.com/mahdihp
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SahamSalanehDto extends BaseDto {

    private Integer sahamSalanehId;

    @NotNull
    private Integer yesrs; //سال
    @NotNull
    private Long membershipFee; // حق عضویت
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;

    public SahamSalanehDto() {
    }
}
