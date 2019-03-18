package com.mahdi.sandogh.model.sahamsalaneh.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDTO;
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
public class ListSahamSalanehDTO extends BaseDTO {

    private List<SahamSalanehDTO> data;

    public ListSahamSalanehDTO() {
    }
}
