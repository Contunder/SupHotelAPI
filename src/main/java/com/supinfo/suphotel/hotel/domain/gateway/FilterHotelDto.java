package com.supinfo.suphotel.hotel.domain.gateway;

import lombok.Data;

import java.sql.Date;

@Data
public class FilterHotelDto {
    private String city;
    private Date dateIn;
    private Date dateOut;
    private int people;
}
