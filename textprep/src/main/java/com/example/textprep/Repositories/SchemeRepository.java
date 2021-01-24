package com.example.textprep.Repositories;


import com.example.textprep.entity.SchemeDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface SchemeRepository extends JpaRepository<SchemeDb, String> {

    @Query(value = "SELECT currval('id')", nativeQuery = true)
    String GetLastId();

    @Query(value = "INSERT INTO files (name, type, user_id, data) VALUES (?1, ?2, ?3, ?4) returning id", nativeQuery = true)
    String saveAndReturnId(String name, String type, String userId, BigInteger data);

    @Query(value = "SELECT * from schemas where user_id = ?1", nativeQuery = true)
    List<SchemeDb> getUserSchemas(String userId);

    @Query(value = "SELECT * from schemas s left join shared_schemas ss ON s.id = ss.schema_id " +
            "JOIN groups g on ss.group_id = g.id join groups_members gm on gm.group_id = g.id " +
            "where gm.user_id = ?1", nativeQuery = true)
    List<SchemeDb> getUserGroupSchemas(String userId);
}
