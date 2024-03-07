package com.supinfo.suphotel.hotel.domain.usecase;

import com.supinfo.suphotel.exception.ResourceNotFoundException;
import com.supinfo.suphotel.hotel.infrastructure.HotelRepository;
import com.supinfo.suphotel.hotel.infrastructure.model.Hotel;
import org.springframework.stereotype.Component;

@Component
public class DeleteHotel {

    private final HotelRepository hotelRepository;

    public DeleteHotel(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public String execute(String hotelName) {
        Hotel hotel = hotelRepository.getHotelByName(hotelName)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Hotel", "Name", hotelName)
                );

        hotelRepository.delete(hotel);

        return "Hotel " + hotelName + " deleted successfully.";
    }
}
