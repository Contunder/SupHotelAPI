package com.supinfo.suphotel.hotel.application;

import com.supinfo.suphotel.hotel.domain.gateway.FilterHotelDto;
import com.supinfo.suphotel.hotel.domain.gateway.HotelsResponse;
import com.supinfo.suphotel.hotel.domain.usecase.*;
import com.supinfo.suphotel.user.application.UserController;
import com.supinfo.suphotel.user.domain.gateway.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;


@ExtendWith(MockitoExtension.class)
class HotelControllerTest {

    @Mock
    private CreateHotel createHotel;
    @Mock
    private ReadHotel readHotel;
    @Mock
    private ReadHotelWithFilter readHotelWithFilter;
    @Mock
    private UpdateHotel updateHotel;
    @Mock
    private DeleteHotel deleteHotel;

    private HotelController hotelController;

    @BeforeEach
    void setup() {
        hotelController = new HotelController(createHotel, readHotel, readHotelWithFilter, updateHotel, deleteHotel);
    }

    @Test
    void testGetAllHotel() {
        // Arrange
        HotelsResponse hotelsResponse = new HotelsResponse();
        Mockito.when(readHotel.execute(0, 10, "id", "asc")).thenReturn(hotelsResponse);

        // Act
        ResponseEntity<HotelsResponse> response = hotelController.getAllHotel(0 ,10 ,"id","asc");

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(new HotelsResponse(), response.getBody());
        Mockito.verify(readHotel).execute(0, 10, "id", "asc");
    }

    @Test
    void testReadHotelbyFilter() {
        // Arrange
        FilterHotelDto filterHotelDto = new FilterHotelDto();
        filterHotelDto.setCity("test");
        filterHotelDto.setDateIn(Date.valueOf("2024-03-21"));
        filterHotelDto.setDateOut(Date.valueOf("2024-03-21"));
        filterHotelDto.setPeople(2);
        HotelsResponse hotelsResponse = new HotelsResponse();
        Mockito.when(readHotelWithFilter.execute(0, 10, "id", "asc",filterHotelDto)).thenReturn(hotelsResponse);

        // Act
        ResponseEntity<HotelsResponse> response = hotelController.readHotelbyFilter(filterHotelDto,0 ,10 ,"id","asc");

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(new HotelsResponse(), response.getBody());
        Mockito.verify(readHotelWithFilter).execute(0, 10, "id", "asc", filterHotelDto);
    }

}