package com.supinfo.suphotel.hotel.application;

import com.supinfo.suphotel.hotel.domain.gateway.FilterHotelDto;
import com.supinfo.suphotel.hotel.domain.gateway.HotelDto;
import com.supinfo.suphotel.hotel.domain.gateway.HotelsResponse;
import com.supinfo.suphotel.hotel.domain.usecase.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

import static com.supinfo.suphotel.utils.AppConstants.*;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    private final CreateHotel createHotel;
    private final ReadHotel readHotel;
    private final ReadHotelWithFilter readHotelWithFilter;
    private final UpdateHotel updateHotel;
    private final DeleteHotel deleteHotel;

    public HotelController(CreateHotel createHotel, ReadHotel readHotel, ReadHotelWithFilter readHotelWithFilter, UpdateHotel updateHotel, DeleteHotel deleteHotel) {
        this.createHotel = createHotel;
        this.readHotel = readHotel;
        this.readHotelWithFilter = readHotelWithFilter;
        this.updateHotel = updateHotel;
        this.deleteHotel = deleteHotel;
    }

    @GetMapping(value = {"/get"})
    public ResponseEntity<HotelsResponse> getAllHotel(
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return ResponseEntity.ok(readHotel.execute(pageNo, pageSize, sortBy, sortDir));
    }

    @PostMapping(value = {"/read/filter"})
    public ResponseEntity<HotelsResponse> readHotelbyFilter(
            @Valid @RequestBody FilterHotelDto filterHotelDto,
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return ResponseEntity.ok(readHotelWithFilter.execute(pageNo, pageSize, sortBy, sortDir, filterHotelDto));
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HotelDto> createHotel(@Valid @RequestBody HotelDto hotelDto){

        return new ResponseEntity<>(createHotel.execute(hotelDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<HotelDto> updateHotel(@Valid @RequestBody HotelDto hotelDto){

        return new ResponseEntity<>(updateHotel.execute(hotelDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteResource(@PathVariable(name = "name") String name){

        return new ResponseEntity<>(deleteHotel.execute(name), HttpStatus.OK);
    }

}
