package com.supinfo.suphotel.user.domain.usecase;

import com.supinfo.suphotel.user.domain.gateway.RegisterDto;
import com.supinfo.suphotel.user.domain.gateway.UserDto;
import com.supinfo.suphotel.user.domain.mapper.UserMapper;
import com.supinfo.suphotel.user.infrastructure.RoleRepository;
import com.supinfo.suphotel.user.infrastructure.UserRepository;
import com.supinfo.suphotel.user.infrastructure.model.Role;
import com.supinfo.suphotel.user.infrastructure.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

public class UpdateUser {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UpdateUser(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userMapper = new UserMapper();
    }

    public UserDto execute(RegisterDto registerDto) {

        User user = userRepository.findUserByEmail(registerDto.getEmail());
        user.setName(registerDto.getName());
        user.setLastName(registerDto.getLastName());
        user.setBirthday(registerDto.getBirthday());
        user.setAddress(registerDto.getAddress());
        user.setZipCode(registerDto.getZipCode());
        user.setCity(registerDto.getCity());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setVerified(false);
        user.setDisabled(false);

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").orElse(new Role(1, "ROLE_USER"));
        roles.add(userRole);
        user.setRoles(roles);

        return userMapper.mapToDTO(userRepository.save(user));
    }
}
