package ru.mike.diploma.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mike.diploma.model.User;
import ru.mike.diploma.services.UserService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(AdminUserController.URL)
public class AdminUserController {
    static final String URL = "/rest/admin/users";
    final static Logger LOG = LoggerFactory.getLogger(AdminUserController.class);
    @Autowired
    UserService userService;

    @GetMapping(value = "/{id}")
    public User get(@PathVariable("id") int id) {
        LOG.info("id user ={}", id);
        return userService.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> add(@Valid @RequestBody User user) {
        User addUser = userService.add(user);
        LOG.info("User {}", addUser);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL + "/{id}")
                .buildAndExpand(addUser.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(addUser);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> update(@Valid @RequestBody User user) {
        User updateUser = userService.add(user);
        LOG.info("User {}", updateUser);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL + "/{id}")
                .buildAndExpand(updateUser.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(updateUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        userService.delete(id);
    }

}
