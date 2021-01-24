package com.example.textprep.Repositories;

import com.example.textprep.entity.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupMemberRepository extends JpaRepository<GroupMember, String> {
}
