package com.supinfo.suphotel.user.domain.usecase;

import com.supinfo.suphotel.user.domain.gateway.RegisterDto;
import com.supinfo.suphotel.user.infrastructure.RoleRepository;
import com.supinfo.suphotel.user.infrastructure.UserRepository;
import com.supinfo.suphotel.user.infrastructure.model.Role;
import com.supinfo.suphotel.user.infrastructure.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private Register register;

    @Test
    void testRegister() {
        // Arrange
        RegisterDto signUpDto = new RegisterDto();
        signUpDto.setEmail("john.doe@example.com");
        signUpDto.setName("John");
        signUpDto.setLastName("Doe");
        signUpDto.setPassword("password");
        signUpDto.setBirthday(new Date(2023,7,6));
        signUpDto.setZipCode("12345");
        signUpDto.setCity("City");
        signUpDto.setAddress("Adress");
        Role userRole = new Role();
        userRole.setId(1L);
        userRole.setName("ROLE_USER");
        User savedUser = new User();
        savedUser.setId(1L);

        when(userRepository.existsByEmail(signUpDto.getEmail())).thenReturn(false);
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(userRole));
        when(passwordEncoder.encode(signUpDto.getPassword())).thenReturn("encoded_password");
        when(userRepository.save(Mockito.any(User.class))).thenReturn(savedUser);

        // Act
        String result = register.execute(signUpDto);

        // Assert
        assertEquals("User registered successfully!.", result);
    }

}