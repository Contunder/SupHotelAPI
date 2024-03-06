package com.supinfo.suphotel.hotel.domain.usecase;

import com.supinfo.suphotel.exception.ResourceNotFoundException;
import com.supinfo.suphotel.hotel.domain.gateway.HotelDto;
import com.supinfo.suphotel.hotel.domain.gateway.HotelsResponse;
import com.supinfo.suphotel.hotel.domain.mapper.HotelMapper;
import com.supinfo.suphotel.hotel.infrastructure.HotelRepository;
import com.supinfo.suphotel.hotel.infrastructure.model.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ReadHotel {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public ReadHotel(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = new HotelMapper();
    }

    public HotelsResponse execute(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        return hotelMapper.mapToHotelResponse(
                hotelRepository.findAll(pageable).getContent().stream().map(hotelMapper::mapToDTO).toList(),
                hotelRepository.findAll(pageable)
        );
    }

}
