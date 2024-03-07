package com.supinfo.suphotel.hotel.infrastructure;

import com.supinfo.suphotel.hotel.infrastructure.model.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Optional<Hotel> getHotelByName(String name);
    Page<Hotel> getHotelByCity(String city, Pageable pageable);

}