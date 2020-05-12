package ru.mike.diploma.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.mike.diploma.model.Vote;
import ru.mike.diploma.services.VoteService;
import ru.mike.diploma.web.json.JsonUtil;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.mike.diploma.testData.RestaurantAndMenuTestData.*;
import static ru.mike.diploma.testData.UserTestData.*;
import static ru.mike.diploma.testData.VoteTestData.*;
import static ru.mike.diploma.web.UtilWebTest.contentJson;
import static ru.mike.diploma.web.UtilWebTest.userHttpBasic;

public class ProfileVoteControllerTest extends AbstractControllerTest {
    @Autowired
    VoteService voteService;
    public final static String GET_URL = ProfileVoteController.GET_URL;
    public final static String POST_URL = ProfileVoteController.POST_URL + "/";

    @Test
    public void get() throws Exception {

        perform(MockMvcRequestBuilders.get(GET_URL)
                .with(userHttpBasic(USER_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(VOTE_1));
    }

    @Test
    public void createOrUpdate() throws Exception {
        ResultActions resultActions = perform(MockMvcRequestBuilders.post(POST_URL, REST2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isCreated());

        Vote returned = UtilWebTest.readFromJson(resultActions,Vote.class);
        Vote created = new Vote(LocalDate.now(),KFC,ADMIN);
        created.setId(returned.getId());
        assertThat(returned).isEqualTo(created);
    }

    @Test
    public void getAllByUserIdAndDate() throws Exception{
        Vote todayVote = getTodayVote();
        voteService.save(todayVote);

        ResultActions resultActions = perform(MockMvcRequestBuilders.get(GET_URL + "/allTodayByUser")
                .with(userHttpBasic(USER_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(todayVote));
        Vote returned = UtilWebTest.readFromJson(resultActions,Vote.class);
        todayVote.setId(returned.getId());
        assertThat(returned).isEqualTo(todayVote);
    }

}