package com.smartlock.server.repositories;

import com.smartlock.server.entities.Door;
import com.smartlock.server.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoorRepository extends JpaRepository<Door, Integer> {

    @Query(value = """
    SELECT d.doorid, d.door_name, d.key_id FROM user u
    JOIN user_door ud ON u.userid = ud.user_id
    JOIN door d ON ud.door_id = d.doorid
    JOIN door_key dk ON dk.keyid = d.key_id
    WHERE u.userid = 1;
""", nativeQuery = true)
    List<Door> findAllByUserId(@Param("id") int id);
}
