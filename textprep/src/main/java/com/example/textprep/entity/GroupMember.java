package com.example.textprep.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "groups_members")
public class GroupMember {

    @Id
    String id;
    String groupId;
    String userId;

    public GroupMember() {
    }

    public GroupMember(String groupId, String userId) {
        this.id = UUID.randomUUID().toString();
        this.groupId = groupId;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
