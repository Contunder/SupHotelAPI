package com.supinfo.suphotel.user.domain.usecase;

import com.supinfo.suphotel.user.infrastructure.UserRepository;
import com.supinfo.suphotel.user.infrastructure.model.User;

public class DeleteUser {

    private final UserRepository userRepository;

    public DeleteUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String execute(String email) {

        User user = userRepository.findUserByEmail(email);

        userRepository.delete(user);

        return "User " + email + " deleted successfully.";
    }

}
