package com.supinfo.suphotel.hotel.domain.usecase;

import com.supinfo.suphotel.hotel.domain.gateway.HotelDto;
import com.supinfo.suphotel.hotel.domain.gateway.OpenDto;
import com.supinfo.suphotel.hotel.domain.mapper.OpenMapper;
import com.supinfo.suphotel.hotel.infrastructure.OpenRepository;
import org.springframework.stereotype.Component;


@Component
public class SaveOpenDate {
    private final OpenRepository openRepository;
    private final OpenMapper openMapper;

    public SaveOpenDate(OpenRepository openRepository) {
        this.openRepository = openRepository;
        this.openMapper = new OpenMapper();
    }

    public void execute(HotelDto hotelDto){
        for (OpenDto openDto : hotelDto.getOpen()){
            if (!openRepository.existsByDate(openDto.getDate())){
                openRepository.saveAll(openMapper.mapToModel( hotelDto.getOpen()));
            }
        }
    }
}
