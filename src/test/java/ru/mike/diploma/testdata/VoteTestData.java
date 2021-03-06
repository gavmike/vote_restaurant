package ru.mike.diploma.testdata;

import ru.mike.diploma.model.Vote;

import java.time.LocalDate;

import static ru.mike.diploma.testdata.RestaurantAndMenuTestData.KFC;
import static ru.mike.diploma.testdata.RestaurantAndMenuTestData.MACD;
import static ru.mike.diploma.testdata.UserTestData.*;

public class VoteTestData {

    public static final int VOTE_1_ID = 1;
    public static final int VOTE_2_ID = 2;
    public static final int VOTE_3_ID = 3;
    public static final int NEW_VOTE_ID = 4;
    public static final int TODAY_VOTE_ID =5;

    public static final Vote VOTE_1 = new Vote(VOTE_1_ID, LocalDate.parse("2019-10-12"), MACD, USER_2);
    public static final Vote VOTE_2 = new Vote(VOTE_2_ID, LocalDate.parse("2019-10-12"), KFC, USER_1);
    public static final Vote VOTE_3 = new Vote(VOTE_3_ID, LocalDate.parse("2019-11-12"), KFC, ADMIN);
    public static final Vote NEW_VOTE = new Vote(NEW_VOTE_ID, LocalDate.parse("2020-11-12"), KFC, ADMIN);

    public static Vote getUpdatedVote(){
        return new Vote(VOTE_1_ID, LocalDate.parse("2020-07-25"), MACD, USER_2);
    }
    public static Vote getTodayVote(){return new Vote(LocalDate.now(),MACD,USER_1);}



}
