package com.mahdi.sandogh.model.account.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mahdi
 * DateTime: ۱۳/۰۸/۲۰۲۰ - 19:08
 * http://github.com/mahdihp
 * http://gitlab.com/mahdihp
 */


@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountReponse {

    private Integer status;
    private String message;
    private String accountNumber;
    private Boolean active;


    public static final class Builder {
        private AccountReponse accountReponse;

        private Builder() {
            accountReponse = new AccountReponse();
        }

        public static Builder asAccountReponse() {
            return new Builder();
        }

        public Builder withStatus(Integer status) {
            accountReponse.setStatus(status);
            return this;
        }

        public Builder withMessage(String message) {
            accountReponse.setMessage(message);
            return this;
        }

        public Builder withAccountNumber(String accountNumber) {
            accountReponse.setAccountNumber(accountNumber);
            return this;
        }

        public Builder withActive(Boolean active) {
            accountReponse.setActive(active);
            return this;
        }

        public Builder but() {
            return asAccountReponse().withStatus(accountReponse.getStatus()).withMessage(accountReponse.getMessage()).withAccountNumber(accountReponse.getAccountNumber()).withActive(accountReponse.getActive());
        }

        public AccountReponse build() {
            return accountReponse;
        }
    }
}
