package com.mahdi.sandogh.model.sahamsalaneh.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by: mahdi
 * DateTime: ۲۷/۰۸/۲۰۲۰ - 21:36:38
 * Email: mahdip.devsc@gmail.com
 * http://github.com/mahdihp & http://gitlab.com/mahdihp
 */


@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SSResponse {

    private Integer status;
    private String message;
    private Integer ssId;
    private Boolean active;


    public static final class Builder {
        private SSResponse sSResponse;

        private Builder() {
            sSResponse = new SSResponse();
        }

        public static Builder aSSResponse() {
            return new Builder();
        }

        public Builder withStatus(Integer status) {
            sSResponse.setStatus(status);
            return this;
        }

        public Builder withMessage(String message) {
            sSResponse.setMessage(message);
            return this;
        }

        public Builder withSsId(Integer ssId) {
            sSResponse.setSsId(ssId);
            return this;
        }

        public Builder withActive(Boolean active) {
            sSResponse.setActive(active);
            return this;
        }

        public Builder but() {
            return aSSResponse().withStatus(sSResponse.getStatus()).withMessage(sSResponse.getMessage()).withSsId(sSResponse.getSsId()).withActive(sSResponse.getActive());
        }

        public SSResponse build() {
            return sSResponse;
        }
    }
}
