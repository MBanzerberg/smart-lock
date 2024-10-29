package com.smartlock.server.repositories;

import com.smartlock.server.entities.LoginMessage;
import com.smartlock.server.entities.LoginMessageProjection;
import com.smartlock.server.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM user WHERE userid = :id", nativeQuery = true)
    User findUserByLogin(@Param("id") int id);

    User findUserByUsername(@Param("username") String username);

    @Query(value = "SELECT u.userid from user u where username = :username", nativeQuery = true)
    int findUserIdByUsername(@Param("username") String username);

    @Query(value = """
    SELECT u.username, p.password from user u 
    JOIN password p ON u.password_id=p.passwordid
     WHERE u.username = :username
                """, nativeQuery = true)
    Optional<LoginMessageProjection> findUserLoginMessage(@Param("username") String username);

}