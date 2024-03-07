package com.supinfo.suphotel.hotel.domain.usecase;

import com.supinfo.suphotel.hotel.infrastructure.OpenRepository;
import com.supinfo.suphotel.hotel.infrastructure.model.Open;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class ReadOpenDate {

    private final OpenRepository openRepository;

    public ReadOpenDate(OpenRepository openRepository) {
        this.openRepository = openRepository;
    }

    public Open execute(Date date) {
        return openRepository.getOpenByDate(date);
    }
}
