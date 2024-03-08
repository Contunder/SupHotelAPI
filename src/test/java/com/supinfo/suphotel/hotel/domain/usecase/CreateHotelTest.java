package com.supinfo.suphotel.hotel.domain.usecase;

import com.supinfo.suphotel.hotel.domain.gateway.HotelDto;
import com.supinfo.suphotel.hotel.domain.gateway.OpenDto;
import com.supinfo.suphotel.hotel.infrastructure.HotelRepository;
import com.supinfo.suphotel.hotel.infrastructure.model.Hotel;
import com.supinfo.suphotel.hotel.infrastructure.model.Open;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateHotelTest {

    @Mock
    private HotelRepository hotelRepository;
    @Mock
    private SaveOpenDate saveOpenDate;
    private CreateHotel createHotel;

    @BeforeEach
    void setup() {
        createHotel = new CreateHotel(hotelRepository, saveOpenDate);
    }

    @Test
    void testCreateHotel() {
        // Arrange
        HotelDto hotelDto = new HotelDto();
        hotelDto.setOpenIn(new OpenDto(new Date(123)));
        hotelDto.setOpenOut(new OpenDto(new Date(123)));
        Hotel hotel = new Hotel();
        hotel.setOpenIn(new Open(1, new Date(123)));
        hotel.setOpenOut(new Open( 2 ,new Date(123)));

        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotel);

        // Act
        HotelDto result = createHotel.execute(hotelDto);

        // Assert
        HotelDto expectedHotelDto = new HotelDto();
        expectedHotelDto.setOpenIn(new OpenDto(new Date(123)));
        expectedHotelDto.setOpenOut(new OpenDto(new Date(123)));
        Assertions.assertEquals(expectedHotelDto, result);
    }
}