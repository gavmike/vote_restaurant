package ru.mike.diploma.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mike.diploma.model.User;
import ru.mike.diploma.services.UserService;

import java.net.URI;

@RestController
@RequestMapping(AdminUserController.URL)
public class AdminUserController {
    static final String URL = "/admin/user";
    final static Logger LOG = LoggerFactory.getLogger(AdminUserController.class);
    @Autowired
    UserService userService;

    @GetMapping(value = "/get/{Id}")
    public User getUser(@PathVariable("Id") int Id) {
        LOG.info("id user ={}", Id);
        return userService.get(Id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> add(@RequestBody User user) {
        User addUser = userService.add(user);
        LOG.info("User {}", addUser);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL + "/{id}")
                .buildAndExpand(addUser.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(addUser);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> update(@RequestBody User user) {
        User updateUser = userService.add(user);
        LOG.info("User {}", updateUser);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL + "/{id}")
                .buildAndExpand(updateUser.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(updateUser);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        userService.delete(id);
    }

}
