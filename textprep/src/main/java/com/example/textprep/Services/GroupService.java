package com.example.textprep.Services;

import com.example.textprep.Repositories.GroupRepository;
import com.example.textprep.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group createGroup(String name, String userId) {
        Group group = new Group(name, userId);
        groupRepository.save(group);
        return group;
    }

    public Group findGroupById(String id) {
        Group group = groupRepository.findById(id).get();
        return group;
    }

    public List<Group> findByGroupName(String name) {
        return groupRepository.findGroupsByName(name);
    }
}
