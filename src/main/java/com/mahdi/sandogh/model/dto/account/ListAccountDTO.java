package com.mahdi.sandogh.model.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDTO;
import lombok.Data;

import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListAccountDTO extends BaseDTO {

    private List<AccountDTO> data;

    public ListAccountDTO() {
    }
}
