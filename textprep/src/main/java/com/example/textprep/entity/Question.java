package com.example.textprep.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Questions")
public class Question {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private int number;
    private String description;
    private String fileId;

    public Question(int number, String description, String fileId) {
        this.number = number;
        this.description = description;
        this.fileId = fileId;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public String getFileId() {
        return fileId;
    }

    public String getId() {
        return this.id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setFileId(String fileId) {
        this.fileId =  fileId;
    }
}
