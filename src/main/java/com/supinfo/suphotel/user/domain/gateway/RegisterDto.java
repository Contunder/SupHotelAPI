package com.supinfo.suphotel.user.domain.gateway;

import lombok.Data;

import java.sql.Date;

@Data
public class RegisterDto {
    private String name;
    private String lastName;
    private Date birthday;
    private String address;
    private String zipCode;
    private String city;
    private String email;
    private String password;
}
