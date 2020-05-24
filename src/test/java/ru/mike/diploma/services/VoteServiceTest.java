package ru.mike.diploma.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mike.diploma.model.Vote;
import ru.mike.diploma.util.exception.NotFoundException;

import java.time.LocalDate;
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
        assertThat(vote).isEqualToComparingFieldByField(VOTE_1);
    }

    @Test
    public void getAllByRestaurantIdAndDate() {
        assertThat(voteService.getAllByUserIdAndDate(USER_2_ID, LocalDate.parse("2019-10-12"))).isEqualToComparingFieldByField(VOTE_1);
    }

    @Test
    public void getAllByUserIdAndDate() {
        assertThat(voteService.getAllByUserIdAndDate(USER_1_ID, LocalDate.parse("2019-10-12"))).isEqualToComparingFieldByField(VOTE_2);
    }

    @Test
    public void getAllByDate() {
        assertThat(voteService.getAllByDate(LocalDate.parse("2019-10-12"))).isEqualTo(List.of(VOTE_1, VOTE_2));
    }

    @Test
    public void save() {
        voteService.save(NEW_VOTE);
        assertThat(voteService.get(NEW_VOTE_ID)).isEqualToComparingFieldByField(NEW_VOTE);
    }

    @Test(expected = NotFoundException.class)
    public void delete() {
        voteService.delete(VOTE_1_ID);
        voteService.get(VOTE_1_ID);
    }

    @Test
    public void saveOrUpdate() {
        Vote updateVote = getUpdatedVote();
        voteService.saveOrUpdate(updateVote, REST1_ID, USER_2_ID);
        assertThat(voteService.get(VOTE_1_ID)).isEqualToComparingFieldByField(updateVote);
    }
}