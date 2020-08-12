package com.mahdi.sandogh.model.sahamsalaneh.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDto;
import lombok.Data;

import java.util.List;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/18/19
 * Time: 22:29
 * https://github.com/mahdihp
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListSahamSalanehDto extends BaseDto {

    private List<SahamSalanehDto> data;

    public ListSahamSalanehDto() {
    }
}
