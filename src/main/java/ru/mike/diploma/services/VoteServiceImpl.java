package ru.mike.diploma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.mike.diploma.model.Vote;

import ru.mike.diploma.persistence.repository.VoteRepository;
import ru.mike.diploma.persistence.repository.MenuRepository;
import java.time.LocalDate;
import java.util.List;


import static ru.mike.diploma.util.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional
public class VoteServiceImpl implements VoteService {
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    UserService userRepository;
    @Autowired
    RestaurantService restaurantRepository;
    @Autowired
    VoteRepository voteRepository;

    @Override
    public Vote get(int id) {
        return checkNotFoundWithId(voteRepository.getById(id).orElse(null),id);
    }

    @Override
    public List<Vote> getAllByRestaurantIdAndDate(int restId, LocalDate localDate) {
        return voteRepository.getAllByRestaurantIdAndLocalDate(restId, localDate);
    }

    @Override
    public Vote getAllByUserIdAndDate(int userId, LocalDate localDate) {
        return voteRepository.getAllByUserIdAndLocalDate(userId, localDate);
    }

    @Override
    public List<Vote> getAllByDate(LocalDate localDate) {

        return voteRepository.getAllByLocalDate(localDate);
    }

    @Override
    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }

    @Override
    public void delete(int id) {
        get(id);
        voteRepository.deleteById(id);
    }

    @Override
    public Vote saveOrUpdate(Vote vote, int restId, int userId) {
        Assert.notNull(vote,"vote should not be null");
        vote.setRestaurant(restaurantRepository.get(restId));
        vote.setUser(userRepository.get(userId));
        return voteRepository.save(vote);
    }


}
