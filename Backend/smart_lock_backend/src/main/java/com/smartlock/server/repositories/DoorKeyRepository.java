package com.smartlock.server.repositories;

import com.smartlock.server.entities.DoorKey;
import com.smartlock.server.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DoorKeyRepository extends JpaRepository<DoorKey, Integer> {

    @Query(value="SELECT * from door_key where keyid= :id", nativeQuery = true)
    DoorKey findOneByDoorKeyId(@Param("id") int id);
}
