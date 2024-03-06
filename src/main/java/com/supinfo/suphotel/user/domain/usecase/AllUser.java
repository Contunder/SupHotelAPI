package com.supinfo.suphotel.user.domain.usecase;

import com.supinfo.suphotel.user.domain.gateway.UserDto;
import com.supinfo.suphotel.user.domain.mapper.UserMapper;
import com.supinfo.suphotel.user.infrastructure.model.User;
import com.supinfo.suphotel.user.infrastructure.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AllUser {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AllUser(UserRepository userRepository){
        this.userRepository = userRepository;
        this.userMapper = new UserMapper();
    }

    public List<UserDto> execute(String email) {
        User actualUser = userRepository.findUserByEmail(email);
        List<User> users = userRepository.findAll();
        users = users.stream().filter(user -> user != actualUser).collect(Collectors.toList());

        return users.stream().map(userMapper::mapToDTO)
                .collect(Collectors.toList());
    }

}
