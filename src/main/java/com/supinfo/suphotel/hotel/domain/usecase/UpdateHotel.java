package com.supinfo.suphotel.hotel.domain.usecase;

import com.supinfo.suphotel.exception.ResourceNotFoundException;
import com.supinfo.suphotel.hotel.domain.gateway.HotelDto;
import com.supinfo.suphotel.hotel.domain.mapper.HotelMapper;
import com.supinfo.suphotel.hotel.infrastructure.HotelRepository;
import com.supinfo.suphotel.hotel.infrastructure.model.Hotel;
import org.springframework.stereotype.Component;

@Component
public class UpdateHotel {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final SaveOpenDate saveOpenDate;

    public UpdateHotel(HotelRepository hotelRepository, SaveOpenDate saveOpenDate) {
        this.hotelRepository = hotelRepository;
        this.saveOpenDate = saveOpenDate;
        this.hotelMapper = new HotelMapper();
    }

    public HotelDto execute(HotelDto hotelDto) {
        Hotel hotel = hotelRepository.getHotelByName(hotelDto.getName())
                .orElseThrow(
                    () -> new ResourceNotFoundException("Hotel", "Name", hotelDto.getName())
                );

        saveOpenDate.execute(hotelDto);


        return hotelMapper.mapToDTO(
                hotelRepository.save(
                        hotelMapper.mapUpdateToModel(hotelDto, hotel)
                ));
    }

}
