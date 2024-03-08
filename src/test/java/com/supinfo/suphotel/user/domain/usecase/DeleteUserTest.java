package com.supinfo.suphotel.user.domain.usecase;

import com.supinfo.suphotel.hotel.domain.usecase.DeleteHotel;
import com.supinfo.suphotel.hotel.infrastructure.HotelRepository;
import com.supinfo.suphotel.hotel.infrastructure.model.Hotel;
import com.supinfo.suphotel.hotel.infrastructure.model.Open;
import com.supinfo.suphotel.user.infrastructure.UserRepository;
import com.supinfo.suphotel.user.infrastructure.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteUserTest {
    @Mock
    private UserRepository userRepository;
    private DeleteUser deleteUser;

    @BeforeEach
    void setup() {
        deleteUser = new DeleteUser(userRepository);
    }

    @Test
    void testDeleteHotel() {
        // Arrange
        when(userRepository.findUserByEmail("email")).thenReturn(new User());

        // Act
        String result = deleteUser.execute("email");

        // Assert
        Assertions.assertEquals("User email deleted successfully.", result);
    }


}