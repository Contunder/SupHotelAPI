package com.supinfo.suphotel.user.domain.usecase;

import com.supinfo.suphotel.user.domain.gateway.RegisterDto;
import com.supinfo.suphotel.user.domain.gateway.UserDto;
import com.supinfo.suphotel.user.infrastructure.RoleRepository;
import com.supinfo.suphotel.user.infrastructure.UserRepository;
import com.supinfo.suphotel.user.infrastructure.model.Role;
import com.supinfo.suphotel.user.infrastructure.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateUserTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleRepository roleRepository;

    private UpdateUser updateUser;

    @BeforeEach
    void setup() {
        updateUser = new UpdateUser(userRepository, passwordEncoder, roleRepository);
    }

    @Test
    void testUpdateHotel() {
        // Arrange
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail("email");
        registerDto.setPassword("password");
        registerDto.setName("new name");
        User user = new User();
        user.setId(1);
        user.setEmail("email");
        user.setPassword("password");
        user.setName("name");
        when(userRepository.findUserByEmail(registerDto.getEmail())).thenReturn(user);
        when(passwordEncoder.encode(registerDto.getPassword())).thenReturn("MyHash");
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(new Role(1, "ROLE_USER")));
        when(userRepository.save(any())).thenReturn(user);

        // Act
        UserDto result = updateUser.execute(registerDto);

        // Assert
        UserDto expectedUserDto = new UserDto();
        expectedUserDto.setId(1);
        expectedUserDto.setEmail("email");
        expectedUserDto.setName("new name");
        Assertions.assertEquals(expectedUserDto, result);
    }
}