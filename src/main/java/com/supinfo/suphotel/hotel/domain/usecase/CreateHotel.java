package com.supinfo.suphotel.hotel.domain.usecase;

import com.supinfo.suphotel.hotel.domain.gateway.HotelDto;
import com.supinfo.suphotel.hotel.domain.mapper.HotelMapper;
import com.supinfo.suphotel.hotel.infrastructure.HotelRepository;
import com.supinfo.suphotel.hotel.infrastructure.OpenRepository;
import com.supinfo.suphotel.hotel.infrastructure.model.Open;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CreateHotel {

    private final HotelRepository hotelRepository;
    private final OpenRepository openRepository;
    private final HotelMapper hotelMapper;
    private final SaveOpenDate saveOpenDate;

    public CreateHotel(HotelRepository hotelRepository, OpenRepository openRepository, SaveOpenDate saveOpenDate) {
        this.hotelRepository = hotelRepository;
        this.openRepository = openRepository;
        this.saveOpenDate = saveOpenDate;
        this.hotelMapper = new HotelMapper();
    }

    public HotelDto execute(HotelDto hotelDto) {
        saveOpenDate.execute(hotelDto);

        return hotelMapper.mapToDTO(hotelRepository.save(hotelMapper.mapToModel(hotelDto)));
    }
}
