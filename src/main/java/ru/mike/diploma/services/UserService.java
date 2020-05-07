package ru.mike.diploma.services;

import ru.mike.diploma.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User add(User user);

    void delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();

}
