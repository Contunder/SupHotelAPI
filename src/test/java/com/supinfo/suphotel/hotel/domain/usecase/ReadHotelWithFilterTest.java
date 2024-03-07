package com.supinfo.suphotel.hotel.domain.usecase;

import com.supinfo.suphotel.hotel.domain.gateway.FilterHotelDto;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReadHotelWithFilterTest {

    @Mock
    private HotelRepository hotelRepository;

    private ReadHotelWithFilter readHotelWithFilter;

    @BeforeEach
    void setup() {
        readHotelWithFilter = new ReadHotelWithFilter(hotelRepository);
    }

    @Test
    void testReadHotel() {
        // Arrange
        FilterHotelDto filterHotelDto = new FilterHotelDto();
        filterHotelDto.setCity("city");
        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(0, 10, sort);
        Mockito.when(hotelRepository.getHotelByCity(filterHotelDto.getCity(), pageable)).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act
        HotelsResponse hotelsResponse = readHotelWithFilter.execute(0 ,10 ,"id","asc", filterHotelDto);

        // Assert
        HotelsResponse expectedHotelResponse = new HotelsResponse();
        expectedHotelResponse.setContent(new ArrayList<>());
        expectedHotelResponse.setLast(true);
        expectedHotelResponse.setTotalPages(1);
        Assertions.assertEquals(expectedHotelResponse, hotelsResponse);
    }

}