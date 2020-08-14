package com.mahdi.sandogh.model.fund.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by: mahdi
 * DateTime: ۱۴/۰۸/۲۰۲۰ - 13:14:13
 * Email: mahdip.devsc@gmail.com
 * http://github.com/mahdihp & http://gitlab.com/mahdihp
 */

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FundResponse {

    private Integer status;
    private String message;
    private Integer fundId;
    private Boolean active;


    public static final class Builder {
        private FundResponse fundResponse;

        private Builder() {
            fundResponse = new FundResponse();
        }

        public static Builder aFundResponse() {
            return new Builder();
        }

        public Builder withStatus(Integer status) {
            fundResponse.setStatus(status);
            return this;
        }

        public Builder withMessage(String message) {
            fundResponse.setMessage(message);
            return this;
        }

        public Builder withFundId(Integer fundId) {
            fundResponse.setFundId(fundId);
            return this;
        }

        public Builder withActive(Boolean active) {
            fundResponse.setActive(active);
            return this;
        }

        public Builder but() {
            return aFundResponse().withStatus(fundResponse.getStatus()).withMessage(fundResponse.getMessage()).withFundId(fundResponse.getFundId()).withActive(fundResponse.getActive());
        }

        public FundResponse build() {
            return fundResponse;
        }
    }
}
