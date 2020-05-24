package ru.mike.diploma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.mike.diploma.AuthorizedUser;
import ru.mike.diploma.model.User;
import ru.mike.diploma.persistence.repository.UserRepository;

import java.util.List;

import static ru.mike.diploma.util.ValidationUtil.checkNotFound;
import static ru.mike.diploma.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")

public class UserService implements  UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    public User add(User user) {
        Assert.notNull(user,"User should not be null");
        return userRepository.save(user);
    }

    public void delete(int id) {

        userRepository.deleteById(id);
    }

    public User get(int id) {
        return checkNotFoundWithId( userRepository.findById(id).orElse(null),id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(userRepository.getByEmail(email),"email"+email);
    }


    public List<User> getAll() {
        return userRepository.findAll();
    }


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException(email + "not found");
        }
        return new AuthorizedUser(user);
    }
}

