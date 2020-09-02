package com.mahdi.sandogh.model.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/20/19
 * Time: 13:12
 * https://github.com/mahdihp
 */

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListUserResponse extends BaseDto {

    private List<UserResponse> data;

}
