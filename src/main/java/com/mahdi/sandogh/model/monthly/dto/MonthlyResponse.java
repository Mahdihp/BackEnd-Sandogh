package com.mahdi.sandogh.model.monthly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by: mahdi
 * DateTime: ۱۴/۰۸/۲۰۲۰ - 15:30:26
 * Email: mahdip.devsc@gmail.com
 * http://github.com/mahdihp & http://gitlab.com/mahdihp
 */

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MonthlyResponse {

    private Integer status;
    private String message;
    private Integer monthlyId;
    private Boolean active;

    public static final class Builder {
        private MonthlyResponse monthlyResponse;

        private Builder() {
            monthlyResponse = new MonthlyResponse();
        }

        public static Builder aMonthlyResponse() {
            return new Builder();
        }

        public Builder withStatus(Integer status) {
            monthlyResponse.setStatus(status);
            return this;
        }

        public Builder withMessage(String message) {
            monthlyResponse.setMessage(message);
            return this;
        }

        public Builder withMonthlyId(Integer monthlyId) {
            monthlyResponse.setMonthlyId(monthlyId);
            return this;
        }

        public Builder withActive(Boolean active) {
            monthlyResponse.setActive(active);
            return this;
        }

        public Builder but() {
            return aMonthlyResponse().withStatus(monthlyResponse.getStatus()).withMessage(monthlyResponse.getMessage()).withMonthlyId(monthlyResponse.getMonthlyId()).withActive(monthlyResponse.getActive());
        }

        public MonthlyResponse build() {
            return monthlyResponse;
        }
    }
}
