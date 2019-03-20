package com.mahdi.sandogh.model.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDTO;
import lombok.Data;

import java.util.List;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/20/19
 * Time: 13:12
 * https://github.com/mahdihp
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListUserDTO extends BaseDTO {

    private List<UserDTO> data;

    public ListUserDTO() {
    }
}
