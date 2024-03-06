package com.supinfo.suphotel.hotel.domain.gateway;


import com.supinfo.suphotel.hotel.infrastructure.model.Open;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class HotelDto {
    private String name;
    private String address;
    private String city;
    private String country;
    private String description;
    private String pictures;
    private Set<OpenDto> open;

}
