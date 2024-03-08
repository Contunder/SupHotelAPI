package com.supinfo.suphotel.hotel.domain.usecase;

import com.supinfo.suphotel.hotel.domain.gateway.HotelDto;
import com.supinfo.suphotel.hotel.infrastructure.OpenRepository;
import com.supinfo.suphotel.hotel.infrastructure.model.Open;
import org.springframework.stereotype.Component;

@Component
public class SaveOpenDate {
    private final OpenRepository openRepository;

    public SaveOpenDate(OpenRepository openRepository) {
        this.openRepository = openRepository;
    }

    public void execute(HotelDto hotelDto) {
        if (!openRepository.existsByDate(hotelDto.getOpenIn().getDate())) {
            openRepository.save(new Open(hotelDto.getOpenIn().getDate()));
        }
        if (!openRepository.existsByDate(hotelDto.getOpenOut().getDate())) {
            openRepository.save(new Open(hotelDto.getOpenOut().getDate()));
        }
    }
}
