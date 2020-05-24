package ru.mike.diploma.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mike.diploma.model.Vote;
import ru.mike.diploma.util.TimeUtil;
import ru.mike.diploma.util.exception.InvalidTimeException;
import ru.mike.diploma.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.mike.diploma.testdata.RestaurantAndMenuTestData.REST1_ID;
import static ru.mike.diploma.testdata.UserTestData.USER_2_ID;
import static ru.mike.diploma.testdata.UserTestData.USER_1_ID;
import static ru.mike.diploma.testdata.VoteTestData.*;

public class VoteServiceTest extends AbstractServiceTest {
    @Autowired
    VoteService voteService;

    @Test
    public void get() {
        Vote vote = voteService.get(VOTE_1_ID);
        assertThat(vote).isEqualTo(VOTE_1);
        assertThat(vote).isEqualToIgnoringGivenFields(VOTE_1, "user", "restaurant");
    }

    @Test
    public void getAllByRestaurantIdAndDate() {
        assertThat(voteService.getAllByUserIdAndDate(USER_2_ID, LocalDate.parse("2019-10-12"))).isEqualToIgnoringGivenFields(VOTE_1, "user", "restaurant");
    }

    @Test
    public void getAllByUserIdAndDate() {
        assertThat(voteService.getAllByUserIdAndDate(USER_1_ID, LocalDate.parse("2019-10-12"))).isEqualToIgnoringGivenFields(VOTE_2, "user", "restaurant");
    }

    @Test
    public void getAllByDate() {
        assertThat(voteService.getAllByDate(LocalDate.parse("2019-10-12"))).isEqualTo(List.of(VOTE_1, VOTE_2));
    }

    @Test
    public void save() {
        voteService.save(NEW_VOTE);
        assertThat(voteService.get(NEW_VOTE_ID)).isEqualToIgnoringGivenFields(NEW_VOTE, "user", "restaurant");
    }

    @Test(expected = NotFoundException.class)
    public void delete() {
        voteService.delete(VOTE_1_ID);
        voteService.get(VOTE_1_ID);
    }

    @Test
    public void saveOrUpdate() {
        LocalTime localTime = LocalTime.now();
        Vote updateVote = getUpdatedVote();
        try {
            voteService.saveOrUpdate(updateVote, REST1_ID, USER_2_ID);
            assertThat(voteService.get(VOTE_1_ID)).isEqualToIgnoringGivenFields(updateVote, "user", "restaurant");
        } catch (InvalidTimeException e) {
            e.printStackTrace();
        }
    }
}