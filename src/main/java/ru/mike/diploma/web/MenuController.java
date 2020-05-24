package ru.mike.diploma.web;


import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mike.diploma.model.Menu;
import ru.mike.diploma.services.MenuService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping( produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    MenuService menuService;
    static final String URL ="/rest/admin/restaurants/{restId}/menus";
    static final String URL_USER ="/rest/restaurants/{restId}/menus";

    @GetMapping(value =URL_USER+ "/{menuId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu get(@PathVariable(name = "menuId") int menuId, @PathVariable(name = "restId") int restId) {
        log.info("menuId = {}", menuId);
        return menuService.get(menuId, restId);
    }

    @GetMapping(value =URL_USER, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getAllByRestaurantId(@PathVariable(name = "restId") int restId) {
        return menuService.getAllByRestaurantId(restId);
    }

    @PostMapping(value = URL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> create(@PathVariable(name = "restId") int restId, @Valid @RequestBody Menu menu) {
        Menu creatMenu = menuService.add(menu, restId);
        URI uriofNewResource = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{menuId}")
                .buildAndExpand(creatMenu.getId()).toUri();
        return ResponseEntity.created(uriofNewResource).body(creatMenu);
    }

    @DeleteMapping(value = URL+"/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id, @PathVariable("restId") int restId) throws NotFoundException {
        menuService.delete(id, restId);
    }

    @PutMapping(value =URL+ "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> update(@PathVariable(name = "restId") int restId, @Valid @RequestBody Menu menu) {
        Menu creatMenu = menuService.add(menu, restId);
        URI uriofNewResource = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{menuId}")
                .buildAndExpand(creatMenu.getId()).toUri();
        return ResponseEntity.created(uriofNewResource).body(creatMenu);
    }

}
