package com.mahdi.sandogh.model.account.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDto;
import lombok.Data;

import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListAccountDto extends BaseDto {

    private List<AccountDto> data;

    public ListAccountDto() {
    }
}
