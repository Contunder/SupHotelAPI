package com.supinfo.suphotel.user.domain.usecase;

import com.supinfo.suphotel.user.domain.gateway.UserDto;
import com.supinfo.suphotel.user.domain.mapper.UserMapper;
import com.supinfo.suphotel.user.infrastructure.model.User;
import com.supinfo.suphotel.user.infrastructure.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserById {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserById(UserRepository userRepository){
        this.userRepository = userRepository;
        this.userMapper = new UserMapper();
    }

    public UserDto execute(long id) {
        User user = userRepository.findUserById(id);

        return userMapper.mapToDTO(user);
    }
}
