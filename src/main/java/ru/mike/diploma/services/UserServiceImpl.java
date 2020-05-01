package ru.mike.diploma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mike.diploma.AuthorizedUser;
import ru.mike.diploma.model.User;
import ru.mike.diploma.persistence.repository.UserRepository;

import java.util.Optional;

@Service("userService")

@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public User add(User user) {
        return userRepository.save(user);
    }

    public void delete(int userID) {
        userRepository.deleteById(userID);
    }

    public Optional<User> getbyID(int userID) {
        return userRepository.findById(userID);
    }

    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException(email + "not found");
        }
        return new AuthorizedUser(user);
    }
}

