package com.example.textprep.Services;

import com.example.textprep.Repositories.QuestionRepository;
import com.example.textprep.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question uploadQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getQuestionByFileId(String fileId) {

        return questionRepository.findAllQuestionsByFileId(fileId);
    }
}
