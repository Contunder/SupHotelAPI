package com.supinfo.suphotel.hotel.domain.mapper;

import com.supinfo.suphotel.hotel.domain.gateway.OpenDto;
import com.supinfo.suphotel.hotel.infrastructure.model.Open;

import java.util.HashSet;
import java.util.Set;

public class OpenMapper {

    public Set<Open> mapToModel(Set<OpenDto> openDtos){
        Set<Open> opens = new HashSet<>();
        openDtos.forEach(openDto -> opens.add(new Open(openDto.getDate())));

        return opens;
    }
}
