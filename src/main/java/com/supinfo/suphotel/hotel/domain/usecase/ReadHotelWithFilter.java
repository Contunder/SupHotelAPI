package com.supinfo.suphotel.hotel.domain.usecase;

import com.supinfo.suphotel.hotel.domain.gateway.FilterHotelDto;
import com.supinfo.suphotel.hotel.domain.gateway.HotelsResponse;
import com.supinfo.suphotel.hotel.domain.mapper.HotelMapper;
import com.supinfo.suphotel.hotel.infrastructure.HotelRepository;
import com.supinfo.suphotel.hotel.infrastructure.model.Hotel;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

@Component
public class ReadHotelWithFilter {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public ReadHotelWithFilter(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = new HotelMapper();
    }

    public HotelsResponse execute(int pageNo, int pageSize, String sortBy, String sortDir, FilterHotelDto filterHotelDto) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Hotel> hotels = new PageImpl<>(
                hotelRepository.getHotelByCity( filterHotelDto.getCity(), pageable).getContent()
                        .stream()
                        .filter(hotel -> filterHotelDto.getDateIn().after(hotel.getOpenIn().getDate())
                                && filterHotelDto.getDateOut().before(hotel.getOpenOut().getDate()))
                .toList());

        return hotelMapper.mapToHotelResponse(
                hotels.getContent().stream().map(hotelMapper::mapToDTO).toList(),
                hotels
        );

    }
}
