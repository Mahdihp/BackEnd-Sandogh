package com.mahdi.sandogh.model.bank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by: mahdi
 * DateTime: ۱۵/۰۸/۲۰۲۰ - 19:27:54
 * Email: mahdip.devsc@gmail.com
 * http://github.com/mahdihp & http://gitlab.com/mahdihp
 */


@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankResponse {

    private Integer status;
    private String message;
    private Integer bankId;
    private Boolean active;

    public static final class Builder {
        private BankResponse bankResponse;

        private Builder() {
            bankResponse = new BankResponse();
        }

        public static Builder aBankResponse() {
            return new Builder();
        }

        public Builder withStatus(Integer status) {
            bankResponse.setStatus(status);
            return this;
        }

        public Builder withMessage(String message) {
            bankResponse.setMessage(message);
            return this;
        }

        public Builder withBankId(Integer bankId) {
            bankResponse.setBankId(bankId);
            return this;
        }

        public Builder withActive(Boolean active) {
            bankResponse.setActive(active);
            return this;
        }

        public Builder but() {
            return aBankResponse().withStatus(bankResponse.getStatus()).withMessage(bankResponse.getMessage()).withBankId(bankResponse.getBankId()).withActive(bankResponse.getActive());
        }

        public BankResponse build() {
            return bankResponse;
        }
    }
}
