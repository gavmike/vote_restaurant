package ru.mike.diploma;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

import static java.util.Objects.requireNonNull;

public class AuthorizedUser extends User {
    private ru.mike.diploma.model.User userModel;
    private static final long serialVersionUID = 1L;

    public AuthorizedUser(ru.mike.diploma.model.User user) {
        super(user.getName(), user.getPassword(), true, true, true, true, Collections.singleton(user.getRole()));
        userModel = user;
    }

    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public int getId() {
        return userModel.getId();
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public void update(ru.mike.diploma.model.User newTo) {
        userModel = newTo;
    }

    public ru.mike.diploma.model.User getUserTo() {
        return userModel;
    }

    @Override
    public String toString() {
        return userModel.toString();
    }

}
