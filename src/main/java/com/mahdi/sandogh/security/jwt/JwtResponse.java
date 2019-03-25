package com.mahdi.sandogh.security.jwt;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JwtResponse {
    private String token;
    private String type = "Bearer";

    public JwtResponse(String accessToken) {
        this.token = accessToken;
    }

}