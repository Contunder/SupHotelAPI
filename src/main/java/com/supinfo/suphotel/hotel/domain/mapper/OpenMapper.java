package com.supinfo.suphotel.hotel.domain.mapper;

import com.supinfo.suphotel.hotel.domain.gateway.OpenDto;
import com.supinfo.suphotel.hotel.infrastructure.model.Open;

import java.util.HashSet;
import java.util.Set;

public class OpenMapper {

    public Open mapToModel(OpenDto openDtos){

        return  new Open(openDtos.getDate());
    }
}
