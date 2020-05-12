package ru.mike.diploma.web;

import jdk.net.SocketFlow;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.mike.diploma.services.VoteService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.mike.diploma.testData.UserTestData.ADMIN;
import static ru.mike.diploma.testData.UserTestData.USER_1;
import static ru.mike.diploma.testData.VoteTestData.*;
import static ru.mike.diploma.web.UtilWebTest.contentJson;
import static ru.mike.diploma.web.UtilWebTest.userHttpBasic;

public class AdminVoteControllerTest extends AbstractControllerTest {
    @Autowired
    VoteService voteService;
    private static final String URL =AdminVoteController.URL +"/";

    @Test
    public void getAllToday() throws Exception {
        perform(MockMvcRequestBuilders.get(URL+"/today/all")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(Collections.emptyList()));
    }

    @Test
    public void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(URL+VOTE_1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());

        assertThat(voteService.getAllByDate(LocalDate.parse("2019-10-12"))).isEqualTo(List.of(VOTE_2));


    }

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL+VOTE_1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(VOTE_1));

    }
}