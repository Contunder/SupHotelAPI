package com.supinfo.suphotel.user.domain.usecase;

import com.supinfo.suphotel.user.domain.gateway.UserDto;
import com.supinfo.suphotel.user.infrastructure.model.User;
import com.supinfo.suphotel.user.infrastructure.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserByEmailTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserByEmail userByEmail;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
    }

    @Test
    void getUserByEmail() {
        // Arrange
        when(userRepository.findUserByEmail("johndoe@example.com")).thenReturn(user);

        // Act
        UserDto userDto = userByEmail.execute("johndoe@example.com");

        // Assert
        assertAll(
                () -> assertEquals(user.getId(), userDto.getId()),
                () -> assertEquals(user.getName(), userDto.getName()),
                () -> assertEquals(user.getLastName(), userDto.getLastName()),
                () -> assertEquals(user.getEmail(), userDto.getEmail())
        );
    }

}