package com.supinfo.suphotel.user.application;

import com.supinfo.suphotel.user.domain.gateway.UserDto;
import com.supinfo.suphotel.security.JwtAuthenticationFilter;
import com.supinfo.suphotel.security.JwtTokenProvider;
import com.supinfo.suphotel.user.domain.usecase.AllUser;
import com.supinfo.suphotel.user.domain.usecase.UserByEmail;
import com.supinfo.suphotel.user.domain.usecase.UserById;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserByEmail userByEmail;
    private final UserById userById;
    private final AllUser allUser;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(UserByEmail userByEmail, UserById userById, AllUser allUser, JwtAuthenticationFilter jwtAuthenticationFilter, JwtTokenProvider jwtTokenProvider){
        this.userByEmail = userByEmail;
        this.userById = userById;
        this.allUser = allUser;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping(value = {"/email/{email}"})
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(userByEmail.execute(email));
    }

    @GetMapping(value = {"/id/{id}"})
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok(userById.execute(id));
    }

    @GetMapping(value = {"/actual"})
    public ResponseEntity<UserDto> getUser(HttpServletRequest request) {
        String token = jwtAuthenticationFilter.getTokenFromRequest(request);
        jwtTokenProvider.validateToken(token);
        String email = jwtTokenProvider.getUsername(token);

        return ResponseEntity.ok(userByEmail.execute(email));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUser(HttpServletRequest request){
        String token = jwtAuthenticationFilter.getTokenFromRequest(request);
        jwtTokenProvider.validateToken(token);
        String email = jwtTokenProvider.getUsername(token);

        return ResponseEntity.ok(allUser.execute(email));
    }

}
