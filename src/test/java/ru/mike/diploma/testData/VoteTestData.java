package ru.mike.diploma.testData;

import ru.mike.diploma.model.Vote;

import java.time.LocalDate;

import static ru.mike.diploma.testData.RestaurantAndMenuTestData.KFC;
import static ru.mike.diploma.testData.RestaurantAndMenuTestData.MACD;
import static ru.mike.diploma.testData.UserTestData.*;

public class VoteTestData {

    public static final int VOTE_1_ID = 1;
    public static final int VOTE_2_ID = 2;
    public static final int VOTE_3_ID = 3;
    public static final int NEW_VOTE_ID = 4;

    public static final Vote VOTE_1 = new Vote(VOTE_1_ID, LocalDate.parse("2019-10-12"), MACD, dive);
    public static final Vote VOTE_2 = new Vote(VOTE_2_ID, LocalDate.parse("2019-10-12"), KFC, mike);
    public static final Vote VOTE_3 = new Vote(VOTE_3_ID, LocalDate.parse("2019-11-12"), KFC, chak);
    public static final Vote NEW_VOTE = new Vote(NEW_VOTE_ID, LocalDate.parse("2020-11-12"), KFC, chak);

    public static Vote getUpdatedVote(){
        return new Vote(VOTE_1_ID, LocalDate.parse("2020-07-25"), MACD, dive);
    }



}
