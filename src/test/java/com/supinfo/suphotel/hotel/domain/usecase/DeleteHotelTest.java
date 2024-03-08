package com.supinfo.suphotel.hotel.domain.usecase;

import com.supinfo.suphotel.hotel.infrastructure.HotelRepository;
import com.supinfo.suphotel.hotel.infrastructure.model.Hotel;
import com.supinfo.suphotel.hotel.infrastructure.model.Open;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteHotelTest {

    @Mock
    private HotelRepository hotelRepository;
    private DeleteHotel deleteHotel;

    @BeforeEach
    void setup() {
        deleteHotel = new DeleteHotel(hotelRepository);
    }

    @Test
    void testDeleteHotel() {
        // Arrange
        Hotel hotel = new Hotel();
        hotel.setOpenIn(new Open(1, new Date(123)));
        hotel.setOpenOut(new Open( 2 ,new Date(123)));

        when(hotelRepository.getHotelByName("Hotel")).thenReturn(Optional.of(hotel));

        // Act
        String result = deleteHotel.execute("Hotel");

        // Assert
        Assertions.assertEquals("Hotel Hotel deleted successfully.", result);
    }
}