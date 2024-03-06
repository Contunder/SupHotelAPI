package com.supinfo.suphotel.user.infrastructure;

import com.supinfo.suphotel.user.infrastructure.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    User findUserByEmail(String email);
    User findUserById(long id);
    Boolean existsByEmail(String email);

}
