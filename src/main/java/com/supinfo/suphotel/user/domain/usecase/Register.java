package com.supinfo.suphotel.user.domain.usecase;

import com.supinfo.suphotel.exception.SupHotelAPIException;
import com.supinfo.suphotel.user.domain.gateway.RegisterDto;
import com.supinfo.suphotel.user.infrastructure.RoleRepository;
import com.supinfo.suphotel.user.infrastructure.UserRepository;
import com.supinfo.suphotel.user.infrastructure.model.Role;
import com.supinfo.suphotel.user.infrastructure.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Register {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public Register(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public String execute(RegisterDto registerDto) {

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new SupHotelAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        User user = new User();
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

        userRepository.save(user);

        return "User registered successfully!.";
    }

}
