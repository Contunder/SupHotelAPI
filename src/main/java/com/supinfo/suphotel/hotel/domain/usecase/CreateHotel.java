package com.supinfo.suphotel.hotel.domain.usecase;

import com.supinfo.suphotel.hotel.domain.gateway.HotelDto;
import com.supinfo.suphotel.hotel.domain.mapper.HotelMapper;
import com.supinfo.suphotel.hotel.infrastructure.HotelRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateHotel {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final SaveOpenDate saveOpenDate;

    public CreateHotel(HotelRepository hotelRepository, SaveOpenDate saveOpenDate) {
        this.hotelRepository = hotelRepository;
        this.saveOpenDate = saveOpenDate;
        this.hotelMapper = new HotelMapper();
    }

    public HotelDto execute(HotelDto hotelDto) {
        saveOpenDate.execute(hotelDto);

        return hotelMapper.mapToDTO(hotelRepository.save(hotelMapper.mapToModel(hotelDto)));
    }
}
