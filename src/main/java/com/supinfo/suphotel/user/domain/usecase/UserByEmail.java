package com.supinfo.suphotel.user.domain.usecase;

import com.supinfo.suphotel.user.domain.gateway.UserDto;
import com.supinfo.suphotel.user.domain.mapper.UserMapper;
import com.supinfo.suphotel.user.infrastructure.model.User;
import com.supinfo.suphotel.user.infrastructure.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserByEmail {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserByEmail(UserRepository userRepository){
        this.userRepository = userRepository;
        this.userMapper = new UserMapper();
    }

    public UserDto execute(String email) {
        User user = userRepository.findUserByEmail(email);
        return userMapper.mapToDTO(user);
    }

}
