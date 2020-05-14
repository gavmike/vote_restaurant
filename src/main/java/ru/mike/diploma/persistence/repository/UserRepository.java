package ru.mike.diploma.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mike.diploma.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    User getByEmail(String emeil);

}
