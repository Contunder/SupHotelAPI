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
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateHotelTest {

    @Mock
    private HotelRepository hotelRepository;
    @Mock
    private SaveOpenDate saveOpenDate;
    private UpdateHotel updateHotel;

    @BeforeEach
    void setup() {
        updateHotel = new UpdateHotel(hotelRepository, saveOpenDate);
    }

    @Test
    void testUpdateHotel() {
        // Arrange
        HotelDto hotelDto = new HotelDto();
        hotelDto.setName("Hotel");
        hotelDto.setOpenIn(new OpenDto(new Date(1234)));
        hotelDto.setOpenOut(new OpenDto(new Date(1234)));
        Hotel hotel = new Hotel();
        hotel.setName("Hotel");
        hotel.setOpenIn(new Open(1, new Date(123)));
        hotel.setOpenOut(new Open( 2 ,new Date(123)));

        when(hotelRepository.getHotelByName(hotelDto.getName())).thenReturn(Optional.of(hotel));
        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotel);

        // Act
        HotelDto result = updateHotel.execute(hotelDto);

        // Assert
        HotelDto expectedHotelDto = new HotelDto();
        expectedHotelDto.setName("Hotel");
        expectedHotelDto.setOpenIn(new OpenDto(new Date(1234)));
        expectedHotelDto.setOpenOut(new OpenDto(new Date(1234)));
        Assertions.assertEquals(expectedHotelDto, result);
    }
}