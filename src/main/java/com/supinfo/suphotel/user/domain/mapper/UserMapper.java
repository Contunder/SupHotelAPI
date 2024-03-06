package com.supinfo.suphotel.user.domain.mapper;

import com.supinfo.suphotel.user.domain.gateway.UserDto;
import com.supinfo.suphotel.user.infrastructure.model.User;

public class UserMapper {

    public UserDto mapToDTO(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setBirthday(user.getBirthday());
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());
        userDto.setZipCode(user.getZipCode());
        userDto.setCity(user.getCity());
        userDto.setProfilPicture(user.getProfilePicture());

        return userDto;
    }

}
