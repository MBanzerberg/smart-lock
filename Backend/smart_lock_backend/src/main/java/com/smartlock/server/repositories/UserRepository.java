package com.smartlock.server.repositories;

import com.smartlock.server.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM user WHERE userid = :id", nativeQuery = true)
    User findUserByLogin(@Param("id") int id);

}