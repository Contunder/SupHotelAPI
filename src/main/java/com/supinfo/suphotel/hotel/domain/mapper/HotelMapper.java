package com.supinfo.suphotel.hotel.domain.mapper;

import com.supinfo.suphotel.hotel.domain.gateway.HotelDto;
import com.supinfo.suphotel.hotel.domain.gateway.HotelsResponse;
import com.supinfo.suphotel.hotel.domain.gateway.OpenDto;
import com.supinfo.suphotel.hotel.infrastructure.model.Hotel;
import com.supinfo.suphotel.hotel.infrastructure.model.Open;
import org.springframework.data.domain.Page;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HotelMapper {

    public HotelDto mapToDTO(Hotel hotel) {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setName(hotel.getName());
        hotelDto.setDescription(hotel.getDescription());
        hotelDto.setCity(hotel.getCity());
        hotelDto.setCountry(hotel.getCountry());
        hotelDto.setAddress(hotel.getAddress());
        hotelDto.setPictures(hotel.getPictures());
        hotelDto.setOpen(mapToDto(hotel.getOpens()));

        return hotelDto;
    }

    public Hotel mapUpdateToModel(HotelDto hotelDto, Hotel hotel, Set<Open> opens) {
        hotel.setName(hotelDto.getName());
        hotel.setDescription(hotelDto.getDescription());
        hotel.setCity(hotelDto.getCity());
        hotel.setCountry(hotelDto.getCountry());
        hotel.setAddress(hotelDto.getAddress());
        hotel.setPictures(hotel.getPictures());
        hotel.setOpens(opens);

        return hotel;
    }

    public HotelsResponse mapToHotelResponse(List<HotelDto> content, Page<Hotel> resource){
        HotelsResponse hotelsResponse = new HotelsResponse();
        hotelsResponse.setContent(content);
        hotelsResponse.setPageNo(resource.getNumber());
        hotelsResponse.setPageSize(resource.getSize());
        hotelsResponse.setTotalElements(resource.getTotalElements());
        hotelsResponse.setTotalPages(resource.getTotalPages());
        hotelsResponse.setLast(resource.isLast());

        return hotelsResponse;
    }

    public Hotel mapToModel(HotelDto hotelDto, Set<Open> opens) {
        return mapUpdateToModel(hotelDto, new Hotel(), opens);
    }

    private Set<OpenDto> mapToDto(Set<Open> opens) {
        Set<OpenDto> openDtos = new HashSet<>();
        opens.forEach(open -> openDtos.add(new OpenDto(open.getDate())));

        return openDtos;
    }


}
