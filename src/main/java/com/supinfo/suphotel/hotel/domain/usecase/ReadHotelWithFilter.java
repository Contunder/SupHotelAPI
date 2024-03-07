package com.supinfo.suphotel.hotel.domain.usecase;

import com.supinfo.suphotel.hotel.domain.gateway.HotelsResponse;
import com.supinfo.suphotel.hotel.domain.mapper.HotelMapper;
import com.supinfo.suphotel.hotel.infrastructure.HotelRepository;
import com.supinfo.suphotel.hotel.infrastructure.model.Hotel;
import com.supinfo.suphotel.hotel.infrastructure.model.Open;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ReadHotelWithFilter {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public ReadHotelWithFilter(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = new HotelMapper();
    }

    public HotelsResponse execute(int pageNo, int pageSize, String sortBy, String sortDir, String city, Date dateIn, Date dateOut, int numberPeople) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Hotel> hotels = new PageImpl<>(hotelRepository.getHotelByCity(city, pageable).getContent().stream().filter(hotel -> hotel.getOpenIn().getDate().before(dateIn)
                && hotel.getOpenOut().getDate().after(dateOut)).toList());

        return hotelMapper.mapToHotelResponse(
                hotels.getContent().stream().map(hotelMapper::mapToDTO).toList(),
                hotels
        );

    }
}
