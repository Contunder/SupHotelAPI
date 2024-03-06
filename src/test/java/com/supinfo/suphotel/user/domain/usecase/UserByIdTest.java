package com.supinfo.suphotel.user.domain.usecase;

import com.supinfo.suphotel.user.domain.gateway.UserDto;
import com.supinfo.suphotel.user.infrastructure.UserRepository;
import com.supinfo.suphotel.user.infrastructure.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserByIdTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserById userById;

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
    void getUserById() {
        // Arrange
        when(userRepository.findUserById(1L)).thenReturn(user);

        // Act
        UserDto userDto = userById.execute(1L);

        // Assert
        assertAll(
                () -> assertEquals(user.getId(), userDto.getId()),
                () -> assertEquals(user.getName(), userDto.getName()),
                () -> assertEquals(user.getLastName(), userDto.getLastName()),
                () -> assertEquals(user.getEmail(), userDto.getEmail())
        );
    }

}