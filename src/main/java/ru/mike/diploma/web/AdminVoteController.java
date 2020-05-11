package ru.mike.diploma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.mike.diploma.model.Vote;
import ru.mike.diploma.services.VoteService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminVoteController.URL)
public class AdminVoteController {
    static final String URL = "/rest/admin/vote";
    @Autowired
    VoteService voteService;

    @GetMapping(value = "/today/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAllToday() {
        return voteService.getAllByDate(LocalDate.now());
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        voteService.delete(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote get(@PathVariable("id") int id) {
        return voteService.get(id);
    }

}
