package ru.mike.diploma.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mike.diploma.AuthorizedUser;
import ru.mike.diploma.model.Vote;
import ru.mike.diploma.services.RestaurantService;
import ru.mike.diploma.services.VoteService;
import ru.mike.diploma.util.TimeUtil;

import java.net.URI;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
public class ProfileVoteController {
    private Clock clock;
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    VoteService voteService;
    private static final Logger log = LoggerFactory.getLogger(ProfileVoteController.class);
    static final String POST_URL = "/rest/profile/restaurants/{restaurantId}/votes";
    static final String GET_URL = "/rest/votes";

    @GetMapping(value = GET_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote getVote(@AuthenticationPrincipal AuthorizedUser authorizedUser) {
        log.info("user {} ", authorizedUser.getUserTo());
        return voteService.getAllByUserIdAndDate(authorizedUser.getId(), LocalDate.now());
    }

    @PostMapping(value = POST_URL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createOrUpdate(@PathVariable("restaurantId") int restaurantId, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        Vote vote = voteService.getAllByUserIdAndDate(authorizedUser.getId(), LocalDate.now());
        if (vote == null) {
            Vote voteNew = new Vote(LocalDate.now(), restaurantService.get(restaurantId), authorizedUser.getUserTo());
            voteService.save(voteNew);
            URI uriOfNew = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{restaurantId}")
                    .buildAndExpand(vote.getId()).toUri();
            return ResponseEntity.created(uriOfNew).body(voteNew);
        } else {
            log.info("update vote {} restauratnID {} user {}", vote, restaurantId, authorizedUser.getUserTo());
            LocalTime localTime = LocalTime.now();
            TimeUtil.chekVoteTime(localTime);
            voteService.saveOrUpdate(vote, restaurantId, authorizedUser.getId());
            return ResponseEntity.ok().build();

        }
    }
}
