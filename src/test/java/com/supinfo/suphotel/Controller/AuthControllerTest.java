package com.supinfo.suphotel.Controller;

import com.supinfo.suphotel.user.application.AuthController;
import com.supinfo.suphotel.user.domain.gateway.JWTAuthResponse;
import com.supinfo.suphotel.user.domain.gateway.LoginDto;
import com.supinfo.suphotel.user.domain.gateway.RegisterDto;
import com.supinfo.suphotel.user.domain.usecase.Login;
import com.supinfo.suphotel.user.domain.usecase.Register;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private Login login;
    @Mock
    private Register register;

    @InjectMocks
    private AuthController authController;

    @Test
    void testAuthenticateUser() {
        // Arrange
        LoginDto loginDto = new LoginDto();
        String token = "dummyToken";
        Mockito.when(login.execute(loginDto)).thenReturn(token);

        // Act
        ResponseEntity<JWTAuthResponse> response = authController.authenticateUser(loginDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token, Objects.requireNonNull(response.getBody()).getAccessToken());
        verify(login).execute(loginDto);
    }

    @Test
    void testRegister() {
        // Arrange
        RegisterDto registerDto = new RegisterDto();
        String responseMessage = "User registered successfully!.";
        Mockito.when(register.execute(registerDto)).thenReturn(responseMessage);

        // Act
        ResponseEntity<String> response = authController.register(registerDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseMessage, response.getBody());
        verify(register).execute(registerDto);
    }

}
