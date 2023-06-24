package com.portfolio.designPortfolio.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse {

    private final String jwtToken;

    private final String role;
}
