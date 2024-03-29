package com.supinfo.suphotel.user.application;

import com.supinfo.suphotel.user.domain.gateway.JWTAuthResponse;
import com.supinfo.suphotel.user.domain.gateway.LoginDto;
import com.supinfo.suphotel.user.domain.gateway.RegisterDto;
import com.supinfo.suphotel.user.domain.usecase.Login;
import com.supinfo.suphotel.user.domain.usecase.Register;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final Login login;
    private final Register register;

    public AuthController(Login login, Register register) {
        this.login = login;
        this.register = register;
    }

    @PostMapping(value = {"/login", "/signing"})
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto) {

        return ResponseEntity.ok(new JWTAuthResponse(login.execute(loginDto), "Bearer"));
    }

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){

        return new ResponseEntity<>(register.execute(registerDto), HttpStatus.CREATED);
    }

}