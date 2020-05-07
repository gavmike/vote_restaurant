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
import java.util.Optional;

import static ru.mike.diploma.util.ValidationUtil.checkNotFound;
import static ru.mike.diploma.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")

@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User add(User user) {
        Assert.notNull(user,"User should not be null");
        return userRepository.save(user);
    }
    @Override
    public void delete(int id) {
       get(id);
        userRepository.deleteById(id);
    }
    @Override
    public User get(int id) {
        return checkNotFoundWithId( userRepository.findById(id).orElse(null),id);
    }
    @Override
    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(userRepository.getByEmail(email),"email"+email);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
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

