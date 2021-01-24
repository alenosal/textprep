package com.example.textprep.Services;

import com.example.textprep.Repositories.GroupMemberRepository;
import com.example.textprep.entity.GroupMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupMemberService {

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    public GroupMember addMember(String groupId, String userId) {
        GroupMember groupMember = new GroupMember(groupId, userId);
        return groupMemberRepository.save(groupMember);
    }
}
