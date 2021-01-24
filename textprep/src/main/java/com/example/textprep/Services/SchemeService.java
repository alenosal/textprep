package com.example.textprep.Services;


import com.example.textprep.Repositories.QuestionRepository;
import com.example.textprep.Repositories.SchemeRepository;
import com.example.textprep.entity.DocxInput;
import com.example.textprep.entity.DocxManipulator;
import com.example.textprep.entity.Question;
import com.example.textprep.entity.SchemeDb;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.stream.Stream;


@Service
public class SchemeService {
    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    @Autowired
    private SchemeRepository schemeRepository;

    @Autowired
    private QuestionRepository questionService;

    public SchemeDb uploadFile(MultipartFile file, String userId) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        SchemeDb schemeDb = new SchemeDb(fileName,file.getBytes(), file.getContentType(), userId);
        schemeRepository.save(schemeDb);
        String fileId = schemeDb.getId();
        DocxInput docxInput = new DocxInput(file);

        ArrayList<String> questionList = docxInput.QuestionFinder();
        int numberOfQuestions = questionList.size();
        for(int i =1; i<=numberOfQuestions; i++){
            Question question = new Question(i,questionList.get(i-1), fileId);
            questionService.save(question);
        }

        return schemeDb;
    }

    public SchemeDb getSchemeById(String id) {

        return schemeRepository.findById(id).get();
    }

    public Stream<SchemeDb> getAllFiles() {

        return schemeRepository.findAll().stream();
    }

    public InputStream createFile (String fileId, ArrayList<String> replacements) throws IOException {
        byte[] docByte = getSchemeById(fileId).getData();
        XWPFDocument docx = new XWPFDocument(new ByteArrayInputStream(docByte));
        DocxManipulator docxManipulator = new DocxManipulator(docx);
        return docxManipulator.replacePlaceholders(replacements);
    }


    @Transactional
    public Stream<SchemeDb> getUserSchemes(String userId) {

        return schemeRepository.getUserSchemas(userId).stream();
    }
    @Transactional
    public Stream<SchemeDb> getUserGroupSchemes(String userId) {
        return schemeRepository.getUserGroupSchemas(userId).stream();
    }

}
