package com.supinfo.suphotel.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class SupHotelAPIException extends RuntimeException {

    @Getter
    private final HttpStatus status;
    private final String message;

    public SupHotelAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
