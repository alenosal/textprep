package com.example.textprep.Repositories;

import com.example.textprep.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, String> {

    @Query(value = "select * From questions Where file_id = ?1", nativeQuery = true)
    List<Question> findAllQuestionsByFileId(String fileId);

}
