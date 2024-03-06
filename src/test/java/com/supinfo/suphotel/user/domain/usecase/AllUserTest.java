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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AllUserTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AllUser allUser;

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
    void getAllUser() {
        // Arrange
        List<User> users = new ArrayList<>();
        users.add(user);

        when(userRepository.findAll()).thenReturn(users);
        when(userRepository.findUserByEmail("johndoe@example.com")).thenReturn(user);

        // Act
        List<UserDto> userDtos = allUser.execute("johndoe@example.com");

        // Assert
        assertEquals(0, userDtos.size());
    }

}