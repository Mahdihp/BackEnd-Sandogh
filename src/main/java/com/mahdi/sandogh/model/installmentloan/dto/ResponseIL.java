package com.mahdi.sandogh.model.installmentloan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by: mahdi
 * DateTime: ۲۱/۰۸/۲۰۲۰ - 12:19:12
 * Email: mahdip.devsc@gmail.com
 * http://github.com/mahdihp & http://gitlab.com/mahdihp
 */
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseIL {

    private Integer status;
    private String message;
    private Integer installmentLoanId;
    private Boolean active;


    public static final class Builder {
        private ResponseIL responseIL;

        private Builder() {
            responseIL = new ResponseIL();
        }

        public static Builder aResponseIL() {
            return new Builder();
        }

        public Builder withStatus(Integer status) {
            responseIL.setStatus(status);
            return this;
        }

        public Builder withMessage(String message) {
            responseIL.setMessage(message);
            return this;
        }

        public Builder withInstallmentLoanId(Integer installmentLoanId) {
            responseIL.setInstallmentLoanId(installmentLoanId);
            return this;
        }

        public Builder withActive(Boolean active) {
            responseIL.setActive(active);
            return this;
        }

        public Builder but() {
            return aResponseIL().withStatus(responseIL.getStatus()).withMessage(responseIL.getMessage()).withInstallmentLoanId(responseIL.getInstallmentLoanId()).withActive(responseIL.getActive());
        }

        public ResponseIL build() {
            return responseIL;
        }
    }
}
