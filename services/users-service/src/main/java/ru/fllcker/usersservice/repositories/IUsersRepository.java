package ru.fllcker.usersservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.usersservice.models.User;

@Repository
public interface IUsersRepository extends JpaRepository<User, Long> {
}
