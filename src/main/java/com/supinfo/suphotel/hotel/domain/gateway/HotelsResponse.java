package com.supinfo.suphotel.hotel.domain.gateway;

import com.supinfo.suphotel.hotel.infrastructure.model.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelsResponse {
    private List<HotelDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
