package ru.mike.diploma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.mike.diploma.model.Vote;

import ru.mike.diploma.persistence.repository.VoteRepository;
import ru.mike.diploma.persistence.repository.MenuRepository;
import ru.mike.diploma.util.TimeUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


import static ru.mike.diploma.util.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional
public class VoteService {
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    UserService userRepository;
    @Autowired
    RestaurantService restaurantRepository;
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    RestaurantService restaurantService;


    public Vote get(int id) {
        return checkNotFoundWithId(voteRepository.getById(id).orElse(null), id);
    }


    public List<Vote> getAllByRestaurantIdAndDate(int restId, LocalDate localDate) {
        return voteRepository.getAllByRestaurantIdAndLocalDate(restId, localDate);
    }


    public Vote getAllByUserIdAndDate(int userId, LocalDate localDate) {
        return voteRepository.getAllByUserIdAndLocalDate(userId, localDate);
    }


    public List<Vote> getAllByDate(LocalDate localDate) {
        return voteRepository.getAllByLocalDate(localDate);
    }


    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }


    public void delete(int id) {
        voteRepository.deleteById(id);
    }


    public Vote saveOrUpdate(Vote vote, int restId, int userId) {
        if (vote == null) {
            Vote voteNew = new Vote(LocalDate.now(), restaurantService.get(restId), userRepository.get(userId));
            save(voteNew);
            return voteNew;
        } else {
            LocalTime localTime = LocalTime.now();
            TimeUtil.chekVoteTime(localTime);
            vote.setRestaurant(restaurantRepository.get(restId));
            vote.setUser(userRepository.get(userId));
            voteRepository.save(vote);
            return vote;
        }

    }

}
