package com.supinfo.suphotel.hotel.domain.usecase;

import com.supinfo.suphotel.hotel.domain.gateway.HotelDto;
import com.supinfo.suphotel.hotel.domain.gateway.OpenDto;
import com.supinfo.suphotel.hotel.infrastructure.OpenRepository;
import com.supinfo.suphotel.hotel.infrastructure.model.Open;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveOpenDateTest {

    @Mock
    private OpenRepository openRepository;

    private SaveOpenDate saveOpenDate;

    @BeforeEach
    void setup() {
        saveOpenDate = new SaveOpenDate(openRepository);
    }

    @Test
    void testSaveOpenDate() {
        // Arrange
        HotelDto hotelDto = new HotelDto();
        hotelDto.setOpenIn(new OpenDto(new Date(123)));
        hotelDto.setOpenOut(new OpenDto(new Date(1234)));

        when(openRepository.existsByDate(new Date(123))).thenReturn(false);
        when(openRepository.save(new Open(hotelDto.getOpenIn().getDate()))).thenReturn(new Open(hotelDto.getOpenIn().getDate()));

        // Act
        saveOpenDate.execute(hotelDto);

        // Assert
        HotelDto expectedHotelDto = new HotelDto();
        expectedHotelDto.setOpenIn(new OpenDto(new Date(123)));
        expectedHotelDto.setOpenOut(new OpenDto(new Date(1234)));
        verify(openRepository).save(new Open(expectedHotelDto.getOpenIn().getDate()));
    }

    @Test
    void testSaveOpenDate2() {
        // Arrange
        HotelDto hotelDto = new HotelDto();
        hotelDto.setOpenIn(new OpenDto(new Date(123)));
        hotelDto.setOpenOut(new OpenDto(new Date(1234)));

        when(openRepository.existsByDate(new Date(123))).thenReturn(true);
        when(openRepository.existsByDate(new Date(1234))).thenReturn(false);
        when(openRepository.save(new Open(hotelDto.getOpenOut().getDate()))).thenReturn(new Open(hotelDto.getOpenOut().getDate()));

        // Act
        saveOpenDate.execute(hotelDto);

        // Assert
        HotelDto expectedHotelDto = new HotelDto();
        expectedHotelDto.setOpenIn(new OpenDto(new Date(123)));
        expectedHotelDto.setOpenOut(new OpenDto(new Date(1234)));
        verify(openRepository).save(new Open(expectedHotelDto.getOpenOut().getDate()));
    }

}