package ru.mike.diploma.services;

import ru.mike.diploma.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoteService {

  Vote get(int id);

    List<Vote> getAllByRestaurantIdAndDate(int restId, LocalDate localDate);

    Vote getAllByUserIdAndDate(int userId, LocalDate localDate);

    List<Vote> getAllByDate(LocalDate localDate);

    Vote save(Vote vote);

    void delete(int id);

    Vote saveOrUpdate(Vote vote, int restId, int userId);
}
