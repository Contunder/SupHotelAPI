package com.supinfo.suphotel.hotel.domain.usecase;

import com.supinfo.suphotel.hotel.domain.gateway.HotelsResponse;
import com.supinfo.suphotel.hotel.infrastructure.HotelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class ReadHotelTest {

    @Mock
    private HotelRepository hotelRepository;

    private ReadHotel readHotel;

    @BeforeEach
    void setup() {
        readHotel = new ReadHotel(hotelRepository);
    }

    @Test
    void testReadHotel() {
        // Arrange
        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(0, 10, sort);
        Mockito.when(hotelRepository.findAll(pageable)).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act
        HotelsResponse hotelsResponse = readHotel.execute(0 ,10 ,"id","asc");

        // Assert
        HotelsResponse expectedHotelResponse = new HotelsResponse();
        expectedHotelResponse.setContent(new ArrayList<>());
        expectedHotelResponse.setLast(true);
        expectedHotelResponse.setTotalPages(1);
        Assertions.assertEquals(expectedHotelResponse, hotelsResponse);
    }

}