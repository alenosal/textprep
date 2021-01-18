package com.example.textprep.Repositories;


import com.example.textprep.entity.SchemeDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface SchemeRepository extends JpaRepository<SchemeDb, String> {

    @Query(value = "SELECT currval('id')", nativeQuery = true)
    String GetLastId();

    @Query(value = "INSERT INTO files (name, type, user_id, data) VALUES (?1, ?2, ?3, ?4) returning id", nativeQuery = true)
    String saveAndReturnId(String name, String type, String userId, BigInteger data);
}
