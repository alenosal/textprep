package com.example.textprep.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "schemas")
public class SchemeDb {
    @Id
    private String id;
    private String name;
    private String type;
    private String userId;
    @Lob
    private byte[] data;

    public SchemeDb(){

    }

    public SchemeDb(String name, byte[] data, String type, String userId) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
        this.userId = userId;
        this.data = data;
    }

    public SchemeDb(String fileId, String name, byte[] data, String type, String userId) {
        this.id = fileId;
        this.name = name;
        this.type = type;
        this.userId = userId;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }


}
