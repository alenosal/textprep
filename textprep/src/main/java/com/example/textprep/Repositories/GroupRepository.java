package com.example.textprep.Repositories;

import com.example.textprep.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, String> {

    @Query(value = "SELECT * from groups Where name = '?1'", nativeQuery = true)
    public List<Group> findGroupsByName(String name);
}
