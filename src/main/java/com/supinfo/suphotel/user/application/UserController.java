package com.supinfo.suphotel.user.application;

import com.supinfo.suphotel.user.domain.gateway.RegisterDto;
import com.supinfo.suphotel.user.domain.gateway.UserDto;
import com.supinfo.suphotel.security.JwtAuthenticationFilter;
import com.supinfo.suphotel.security.JwtTokenProvider;
import com.supinfo.suphotel.user.domain.usecase.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserByEmail userByEmail;
    private final UserById userById;
    private final AllUser allUser;
    private final DeleteUser deleteUser;
    private final UpdateUser updateUser;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(UserByEmail userByEmail, UserById userById, AllUser allUser, DeleteUser deleteUser, UpdateUser updateUser, JwtAuthenticationFilter jwtAuthenticationFilter, JwtTokenProvider jwtTokenProvider){
        this.userByEmail = userByEmail;
        this.userById = userById;
        this.allUser = allUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
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

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody RegisterDto registerDto){

        return new ResponseEntity<>(updateUser.execute(registerDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(HttpServletRequest request){
        String token = jwtAuthenticationFilter.getTokenFromRequest(request);
        jwtTokenProvider.validateToken(token);
        String email = jwtTokenProvider.getUsername(token);

        return new ResponseEntity<>(deleteUser.execute(email), HttpStatus.OK);
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
