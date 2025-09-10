package com.eshop.Ecommerce.Service;


import com.eshop.Ecommerce.Security.Request.LoginRequest;
import com.eshop.Ecommerce.Security.Request.SignupRequest;
import com.eshop.Ecommerce.Security.Response.MessageResponse;
import com.eshop.Ecommerce.Security.Response.UserInfoResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface AuthService {

     ResponseEntity<?> login(LoginRequest loginRequest);

    ResponseEntity<MessageResponse> register(SignupRequest signUpRequest);

    ResponseCookie logoutUser();

    ResponseEntity<?> getUserDetail(Authentication authentication);

    Object getAllSellers(Pageable pageDetails);
}