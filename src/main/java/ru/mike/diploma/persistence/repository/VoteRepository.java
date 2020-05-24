package ru.mike.diploma.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mike.diploma.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    //@Query("select v from Vote v join fetch v.user join fetch v.restaurant where v.id=?1")
    Optional<Vote> getById(int voteId);

    //@Query("select v from Vote v join fetch v.user join fetch v.restaurant where v.restaurant.id=?1 and v.localDate=?2")
    List<Vote> getAllByRestaurantIdAndLocalDate(int restId, LocalDate localDate);

   // @Query("select v from Vote v join fetch v.user join fetch v.restaurant where v.user.id=?1 and v.localDate=?2")
    Vote getAllByUserIdAndLocalDate(int userId, LocalDate localDate);

    List<Vote> getAllByLocalDate(LocalDate localDate);
}
