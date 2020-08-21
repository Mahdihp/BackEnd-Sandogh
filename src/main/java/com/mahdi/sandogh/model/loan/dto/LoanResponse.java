package com.mahdi.sandogh.model.loan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by: mahdi
 * DateTime: ۲۱/۰۸/۲۰۲۰ - 12:58:52
 * Email: mahdip.devsc@gmail.com
 * http://github.com/mahdihp & http://gitlab.com/mahdihp
 */


@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanResponse {

    private Integer status;
    private String message;
    private Integer loanId;
    private Boolean active;


    public static final class Builder {
        private LoanResponse loanResponse;

        private Builder() {
            loanResponse = new LoanResponse();
        }

        public static Builder aLoanResponse() {
            return new Builder();
        }

        public Builder withStatus(Integer status) {
            loanResponse.setStatus(status);
            return this;
        }

        public Builder withMessage(String message) {
            loanResponse.setMessage(message);
            return this;
        }

        public Builder withLoanId(Integer loanId) {
            loanResponse.setLoanId(loanId);
            return this;
        }

        public Builder withActive(Boolean active) {
            loanResponse.setActive(active);
            return this;
        }

        public Builder but() {
            return aLoanResponse().withStatus(loanResponse.getStatus()).withMessage(loanResponse.getMessage()).withLoanId(loanResponse.getLoanId()).withActive(loanResponse.getActive());
        }

        public LoanResponse build() {
            return loanResponse;
        }
    }
}
