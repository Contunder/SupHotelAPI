package com.supinfo.suphotel.hotel.domain.usecase;

import com.supinfo.suphotel.hotel.infrastructure.OpenRepository;
import com.supinfo.suphotel.hotel.infrastructure.model.Open;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReadOpenDateTest {

    @Mock
    private OpenRepository openRepository;

    private ReadOpenDate readOpenDate;

    @BeforeEach
    void setup() {
        readOpenDate = new ReadOpenDate(openRepository);
    }

    @Test
    void testReadOpenDate() {
        // Arrange
        Open open = new Open(1, new Date(123));

        when(openRepository.getOpenByDate(new Date(123))).thenReturn(open);

        // Act
        Open result = readOpenDate.execute(new Date(123));

        // Assert
        Open expectedOpen = new Open(1 ,new Date(123));
        Assertions.assertEquals(expectedOpen, result);
    }
}