package ru.mike.diploma.persistence.trash;

import org.springframework.security.core.userdetails.UserDetails;
import ru.mike.diploma.model.User;

import java.util.Optional;

public interface UserRepository2 {
    User add(User user);

    void delete(int id);

    Optional<User> get(int id);

    User getByEmail(String email);

    UserDetails loadUserByUserName(String email);
}
