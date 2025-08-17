package com.eshop.Ecommerce.Security.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoResponse {
    private Long id;
    private String jwtToken;
    private String username;
    private String email;
    private List<String> roles;

    public UserInfoResponse(Long id, String username, List<String> roles, String email, String jwtToken) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.email = email;
        this.jwtToken = jwtToken;
    }

    public UserInfoResponse(Long id, String username, List<String> roles, String email) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.email = email;
    }
}

