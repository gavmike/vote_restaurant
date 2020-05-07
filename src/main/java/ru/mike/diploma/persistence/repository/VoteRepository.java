package ru.mike.diploma.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mike.diploma.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    Optional<Vote> getById(int voteId);

    List<Vote> getAllByRestaurantIdAndLocalDate(int restId, LocalDate localDate);

    Vote getAllByUserIdAndLocalDate(int userId, LocalDate localDate);

    List<Vote> getAllByLocalDate(LocalDate localDate);
}
